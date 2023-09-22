package Tests;

import PageObjects.AccountPage;
import PageObjects.Products;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import org.openqa.selenium.By;
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
                {"larisa.branzan@gmail.com", "Larisa1234!", "chrome", "Casuta de papusi cu mobilier, Little Dutch"},

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
                {"chrome", "Vezi Coșul Nu poți adăuga acea cantitate în coș — avem 4 în stoc și ai deja 4 în coșul tău.  "},

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
        int maxQuantity = 4; // Maximum quantity

        //Prima metoda
//        while (!products.isDisplayedMaxProductInCartMessageError()) {
//            if(products.isProdusAdaugatCuSucces()){
//                products.waitToDisappear();
//            }
//            products.clickonProduct(); //Clicks on Add to cart button
//            Thread.sleep(1000);
//        }
//        Assert.assertTrue(products.isDisplayedMaxProductInCartMessageError());
//        products.clickOnMyCart();
//        Assert.assertEquals(check, maxQuantity);

        //A doua metodao
        for (int i = 0; i <= maxQuantity; i++) {
            products.clickonProduct(); //Clicks on Add to cart button
        }
        Thread.sleep(4000);
        Assert.assertTrue(products.isDisplayedMaxProductInCartMessageError());
        products.clickOnMyCart();
            int check = Integer.parseInt(driver.findElement(By.xpath("//input[@type='number']")).getAttribute("max"));
            Assert.assertEquals(check, maxQuantity);


//        for (int i = 0; i < maxQuantity; i++) {
//
//            products.clickonProduct(); //Clicks on Add to cart button
//            products.clickOnMyCart();
//            products.clickOnPlus();
//
//            if (i < maxQuantity - 1) {
//                // Check if the expected message is not displayed yet
//                Assert.assertNotEquals(products.maxProductInCartMessageError(), message);
//            } else {
//                // Check if the expected message is displayed when the maximum quantity is reached
//                Assert.assertEquals(products.maxProductInCartMessageError(), message);
//            }


            // System.out.println("Added product to cart, Quantity: " + (i + 1));
        }
    }





