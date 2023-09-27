package Tests;

import PageObjects.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.NoAlertPresentException;

public class CheckoutTests extends BaseTest {

    WebDriverWait wait;
    LoginPage loginPage;
    Actions actions;
    Products products;
    AccountPage accountPage;
    CheckOutPage checkOutPage;
    WebElement page;
    RegistrationPage registrationPage;




    @DataProvider(name = "AddToCartAndProceedToCheckOut")
    public Object[][] AddToCartAndProceedToCheckOut() {
        return new Object[][]{
                {"larisa.branzan@gmail.com", "Exchange15!!!!", "chrome", "DETALII FACTURARE"},

        };
    }


    @Test(dataProvider = "AddToCartAndProceedToCheckOut")
    public void AddToCartAndProceedToCheckOut(String username, String password, String browser, String billingMessage) throws InterruptedException {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        System.out.println("Open Browser");

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        System.out.println("Login finished, verify error message");
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getNamePersAccount().contains(username));
        System.out.println("Login user");
        loginPage.goToProducts(driver);
        products = new Products(driver);
        int maxQuantity = 1; // Maximum quantity
        int retry = 0; // Initialize the retry variable


        products.clickonProduct("767199");//Clicks on Add to cart button
        Thread.sleep(3000);
        products.clickOnMyCart();
          Thread.sleep(3000);
            products.goToCheckOut(driver);
            checkOutPage = new CheckOutPage(driver);
            Assert.assertTrue(checkOutPage.billingInfo().contains(billingMessage));
            checkOutPage.enterTextFirstName("Larisa");
            checkOutPage.enterTextLastName("Branzan");
            checkOutPage.enterTextPhone("0784885620");
            checkOutPage.enterEmail("larisa.branzan@gmail.com");
            checkOutPage.


    }

}
