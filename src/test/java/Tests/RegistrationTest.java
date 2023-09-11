package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
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
public class RegistrationTest extends BaseTest {
    RegistrationPage registrationPage;
    AccountPage accountPage;
    LoginPage loginPage;
    WebElement page;


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
                {"chrome","aaa@gmail.com", "Exchange15!", "", "Eroare: Te rog introdu o parolă pentru cont."}
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
        registrationPage.usernameInputRegistration(username);
        registrationPage.passwordInputRegistration(password);

        if (username.length()==0) {
            System.out.println("No username");
            org.testng.Assert.assertEquals(registrationPage.usernameErr(),usernameErr);
        }
        if (password.length() == 0) {
            System.out.println("No password");
            org.testng.Assert.assertEquals(registrationPage.passError(), passErr);
        }
    }
}
