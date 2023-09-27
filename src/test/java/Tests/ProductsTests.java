package Tests;

import PageObjects.AccountPage;
import PageObjects.Products;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
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
                {"larisa.branzan@gmail.com", "Exchange15!!!!", "chrome", "Casuta de papusi cu mobilier, Little Dutch"},

        };
    }


    @Test(dataProvider = "AddToCart")
    public void AddToCart(String username, String password, String browser, String product) {
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
        System.out.println("Logout user");
        loginPage.goToProducts(driver);
        loginPage.clickonProduct();
        loginPage.clickOnMyCart();
        Assert.assertEquals(loginPage.productName(), product);
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
        setUpDriver(browser);
        driver.get(baseUrl);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        accountPage = new AccountPage(driver);
        loginPage.goToProducts(driver);
        products = new Products(driver);
        int maxQuantity = 5; // Maximum quantity
        int retry = 0; // Initialize the retry variable


        products.clickonProduct("750754");//Clicks on Add to cart button
        Thread.sleep(3000);
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

        Assert.assertEquals(products.getCartQTY(), "4");
        System.out.println("Products added with max capacity on");
    }




    @DataProvider(name = "RemoveFromCart")
    public Object[][] RemoveFromCart() {
        return new Object[][]{
                {"chrome", "COȘUL DE CUMPĂRĂTURI ESTE GOL"},

        };
    }

    @Test(dataProvider = "RemoveFromCart")
    public void RemoveFromCart(String browser, String message) throws InterruptedException {
        System.out.println("Order one product and remove from Cart" + "=> on browser:" + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        accountPage = new AccountPage(driver);
        loginPage.goToProducts(driver);
        products = new Products(driver);

        int maxRetry = 2; // Maximum retry
        int retry = 0; // Initialize the retry variable


        products.clickonProduct("767199");//Clicks on Add to cart button
        Thread.sleep(5000);
        products.clickOnMyCart();

//       add multiple products on cart
        for (int i = 0; i < maxRetry; i++) {
            try {
                products.removeProductsFromBin();

                try {

                    driver.switchTo().alert().accept();
                } catch (NoAlertPresentException e) {

                }
            } catch (ElementClickInterceptedException e) {
                retry++;
            }
            System.out.println("Product Removed, Quantity: " + (i + 0));
        }

        products.clickOnMyCart();

        Assert.assertEquals(products.emptyBinMessage(), message);

        }



    }



