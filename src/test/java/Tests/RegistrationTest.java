package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import PageObjects.MyCustomException;
import PageObjects.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegistrationTest extends BaseTest {
    RegistrationPage registrationPage;
    AccountPage accountPage;
    LoginPage loginPage;
    WebElement page;
    WebDriverWait wait;


    @DataProvider(name = "browsers")
    public Object[][] dp() {
        return new Object[][]{
                {"chrome"}
                //,{"edge"}
        };
    }




    @DataProvider(name = "registerNegativeDpUserPassConfirm")
    public Object[][] registerNegativeDpUserPassConfirm() {
        return new Object[][]{

                {"chrome", "", "", "Eroare: Te rog introdu o adresă de email validă.", ""},
                {"chrome", "aaa@gmail.com", "", "", "Eroare: Te rog introdu o parolă pentru cont."},

        };
    }


    @Test(dataProvider = "registerNegativeDpUserPassConfirm")
    public void registerNegativeUserPassConfirm(String browser,
                                                String username,
                                                String password,
                                                String usernameErr,
                                                String passErr
    ) {
        System.out.println("Login with username:" + username +
                " /password:" + password +
                " /confirmationPassword:" +
                " => on browser:" + browser);
        //setUpDriver(browser);
        //driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        System.out.println("Opened login page.");

        loginPage.goToRegistrationPage();
        System.out.println("Opened registration page.");

        registrationPage = new RegistrationPage(driver);
        registrationPage.register(username, password);
        System.out.println("Registration  finished, verify error message");


        if (!usernameErr.isEmpty())
            Assert.assertTrue(registrationPage.getRegisterErr().contains(usernameErr));
        if (!passErr.isEmpty())
            Assert.assertTrue(registrationPage.getRegisterErr().contains(passErr));

    }


    @DataProvider(name = "registerNegativeExistingUser")
    public Object[][] registerNegativeExistingUser() {
        return new Object[][]{

                {"chrome", "alex@yahoo.com", "Rapid1923!!!!!", "Eroare: Este înregistrat deja un cont cu adresa ta de email. Te rog autentifică-te.", ""},

        };
    }


    @Test(dataProvider = "registerNegativeExistingUser")
    public void registerNegativeExistingUser(String browser,
                                                String username,
                                                String password,
                                                String usernameErr,
                                                String passErr
    ) {
        System.out.println("Register with existing username:" + username +
                " /password:" + password +
                " => on browser:" + browser);
        //setUpDriver(browser);
        //driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        System.out.println("Opened login page.");

        loginPage.goToRegistrationPage();
        System.out.println("Opened registration page.");

        registrationPage = new RegistrationPage(driver);
        registrationPage.register(username, password);
        System.out.println("Registration, verify error message");
        Assert.assertEquals(registrationPage.getRegisterErr(), usernameErr);

    }

    @DataProvider(name = "registerPositive")
    public Object[][] registerPositive() {
        return new Object[][]{
                {"chrome","aassadsgfgcccfdasa@gmail.com", "so1sadasaasa2332", "Bine ai venit în pagina contului tău"},
        };
    }


    @Test(dataProvider = "registerPositive")
    public void registerPositive( String browser,
                                             String username,
                                             String password,
                                             String welcome ) {

        //setUpDriver(browser);
        //driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        System.out.println("Opened login page.");
        loginPage.goToRegistrationPage();
        System.out.println("Opened registration page.");
        registrationPage=new RegistrationPage(driver);
        registrationPage.clickAcceptTerms();
        registrationPage.register(username,password);
//        Assert.assertEquals(registrationPage.WelcomeRegister(), welcome );
//        System.out.println(registrationPage.WelcomeRegister());
    }

}

//TEST