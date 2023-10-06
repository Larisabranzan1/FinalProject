package Tests;

import PageObjects.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
            checkOutPage.enterPostal("011333");
            checkOutPage.enterEmail("larisa.branzan@gmail.com");
            checkOutPage.moveToCountryAndClick();
            checkOutPage.clickOnField("România");
            checkOutPage.moveToStateAndClick();
            checkOutPage.clickOnField("Cluj");
            checkOutPage.moveToCityAndClick();
            checkOutPage.clickOnField("Agris");
 //           checkOutPage.clickAcceptTermsAndConditions();
        checkOutPage.enterStreet("Ajustorului");
            checkOutPage.clickOnSubmitOrder();
        Assert.assertTrue(checkOutPage.myOrder().contains("COMANDA TA"));

    }

}
