package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    LoginPage loginPage;
    AccountPage accountPage;


    @FindBy(id = "reg_email")
    private WebElement registerPageBtn;

    @Test
    public void testHomePageTitle() {
        driver.get("https://www.creatoys.ro/");
        String expectedTitle = "Home - Creatoys.ro";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Home page title is not as expected.");
    }

    @DataProvider(name = "loginPDp")
    public Object[][] loginPositiveDataProvider() {
        return new Object[][]{
                {"larisa.branzan@gmail.com", "Exchange15!!!!", "chrome"},

        };
    }

    @Test(dataProvider = "loginPDp")
    public void loginPositive(String username, String password, String browser) {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        System.out.println("Open Browser");

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        System.out.println("Login finished, verify error message");
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getNamePersAccount().contains(username));
        System.out.println("User was logged in with success!");
    }


    @DataProvider(name = "loginNDp1")
    public Object[][] loginNegativeDataProvider1() {
        return new Object[][]{
                {"", "somePassword", "edge", "Eroare: Numele de utilizator este obligatoriu.", ""},
                {"larisa.branzan@gmail.com", "", "firefox", "", "Eroare: câmpul parolă este gol"},
                {"zebra", "zebrapassword", "chrome", "Eroare: numele de utilizator zebra nu este înregistrat pe acest site. Dacă nu îți amintești numele de utilizator, încearcă folosind adresa de email.", ""}
        };
    }

    @Test(dataProvider = "loginNDp1")
    public void loginNegativeDataProvider1(String username, String password, String browser, String usernameErr, String passErr) {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        System.out.println("Login finished, verify error message");

        if (!usernameErr.isEmpty())
            Assert.assertTrue(loginPage.geUsernameErr().contains(usernameErr));
        if (!passErr.isEmpty())
            Assert.assertTrue(loginPage.getPassErr().contains(passErr));

    }

    @DataProvider(name = "loginNDp2")
    public Object[][] loginNegativeDataProvider2() {
        return new Object[][]{
                {"", "", "chrome", "Eroare: Numele de utilizator este obligatoriu.", "Eroare: câmpul parolă este gol"},
        };
    }

    @Test(dataProvider = "loginNDp2")
    public void loginNegativeDataProvider2(String username, String password, String browser, String usernameErr, String passErr) {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        System.out.println("Login finished, verify error message");

        if (username.isEmpty() && password.isEmpty()) {
            Assert.assertTrue(loginPage.geUsernameErr().contains(usernameErr));
        }
    }

}


