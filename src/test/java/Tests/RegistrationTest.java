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

                {"chrome","", "", "Eroare: Te rog introdu o adresă de email validă.", "" },
                {"chrome","aaa@gmail.com", "", "", "Eroare: Te rog introdu o parolă pentru cont."},

        };
    }




    @Test(dataProvider = "registerNegativeDpUserPassConfirm")
    public void registerNegativeUserPassConfirm (String browser,
                                                 String username,
                                                 String password,
                                                 String usernameErr,
                                                 String passErr
    ) {
        System.out.println("Login with username:" + username +
                " /password:" + password +
                " /confirmationPassword:"+
                " => on browser:" + browser);
        //setUpDriver(browser);
        //driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        System.out.println("Opened login page.");
        loginPage.goToRegistrationPage();
        System.out.println("Opened registration page.");
        registrationPage=new RegistrationPage(driver);
        registrationPage.register(username,password);
        System.out.println("Registration  finished, verify error message");
        System.out.println(registrationPage.usernameErr());
        System.out.println(registrationPage.passErr());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='woocommerce-error']//child::li")));
        Assert.assertEquals(registrationPage.usernameErr(), usernameErr);
        Assert.assertEquals(registrationPage.passErr(), passErr);

    }

    @DataProvider(name = "registerPositive")
    public Object[][] registerPositive() {
        return new Object[][]{
                {"chrome","aaa@gmail.com", "so12332","Avem nevoie de acord pentru prelucrarea datelor"},
        };
    }


    @Test(dataProvider = "registerPositive")
    public void registerPositive( String browser,
                                             String username,
                                             String password,
                                             String acceptTermsErr) throws MyCustomException {

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
        System.out.println("Tested message: Avem nevoie de acord dvs");
    }

}

//TEST