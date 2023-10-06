package Tests;

import PageObjects.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class ProductsTests extends BaseTest {

    Products products;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    LoginPage loginPage;
    WebElement page;
    WebDriverWait wait;


    @DataProvider(name = "AddToCart")
    public Object[][] AddToCart() {
        return new Object[][]{
                {"larisa.branzan@gmail.com", "Exchange15!!!!", "chrome", "Masinute pe rampa mare din lemn, Wonderworld"},

        };
    }


    @Test(dataProvider = "AddToCart")
    public void AddToCart(String username, String password, String browser, String product) throws InterruptedException {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        System.out.println("Login finished, verify error message");
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getNamePersAccount().contains(username));
        System.out.println("Logout user");
        loginPage.goToProducts(driver);
        products = new Products(driver);
        products.clickonProduct("767199");
        Thread.sleep(3000);
        products.clickOnMyCart();

            Assert.assertEquals(products.getDollHouseText(), product);
        }


    @DataProvider(name = "AddtoCartUntilReachingMaxQuantity")
    public Object[][] AddtoCartUntilReachingMaxQuantity() {
        return new Object[][]{
                {"chrome", " Nu poți adăuga acea cantitate în coș — avem 4 în stoc și ai deja 4 în coșul tău.  "},

        };
    }


    @Test(dataProvider = "AddtoCartUntilReachingMaxQuantity")
    public void AddtoCartUntilReachingMaxQuantity(String browser, String message) throws InterruptedException {
        System.out.println("Order Product Multiple Times" + "=> on browser:" + browser);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        accountPage = new AccountPage(driver);
        loginPage.goToProducts(driver);
        products = new Products(driver);
        int maxQuantity = 4; // Maximum quantity
        int retry = 0; // Initialize the retry variable


        products.clickonProduct("750754");//Clicks on Add to cart button
        Thread.sleep(5000);
        products.clickOnMyCart();

//       add multiple products on cart
        for (int i = 0; i < maxQuantity; i++) {
            try {
                products.clickOnPlus();
            } catch (ElementClickInterceptedException e) {
                retry++;
            }

            System.out.println("Added product to cart, Quantity: " + (i + 1));
        }

        Assert.assertEquals(products.getCartQTY(), "3");
        System.out.println("Products added with max capacity on");
    }




    @DataProvider(name = "RemoveFromCart")
    public Object[][] RemoveFromCart() {
        return new Object[][]{
                {"chrome", "COȘUL DE CUMPĂRĂTURI ESTE GOL"},

        };
    }

    @Test(dataProvider = "RemoveFromCart")
    public void RemoveFromCart(String browser, String message) throws InterruptedException, MyCustomException {
        System.out.println("Order one product and remove from Cart" + "=> on browser:" + browser);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        accountPage = new AccountPage(driver);
        loginPage.goToProducts(driver);
        products = new Products(driver);
        products.clickonProduct("767199");//Clicks on Add to cart button
        Thread.sleep(6000);
        products.clickOnMyCart();
        int maxRetries = 3;
        int retryCount = 0;
        boolean success = false;

        while (!success && retryCount < maxRetries) {
            try {
                products.removeProductsFromBin();
                success = true;
            } catch (Exception e) {
                // Log the exception or take other appropriate actions
                retryCount++;
            }
        }

        driver.switchTo().alert().accept();
        products.clickOnMyCart();
        Assert.assertEquals(products.emptyBinMessage(), message);
    }

    @Test
    public void myCartIsEmpty(){
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        accountPage = new AccountPage(driver);
        loginPage.goToProducts(driver);
        products = new Products(driver);
        products.clickOnMyCart();
        Assert.assertEquals(products.emptyBinMessage(), "COȘUL DE CUMPĂRĂTURI ESTE GOL");
    }


}



