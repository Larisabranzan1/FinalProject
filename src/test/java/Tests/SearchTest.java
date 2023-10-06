package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import PageObjects.Products;
import PageObjects.RegistrationPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    Products products;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    LoginPage loginPage;
    WebElement page;
    WebDriverWait wait;

    @DataProvider(name = "searchDP")
    public Object[][] searchPositiveDataProvider() {
        return new Object[][]{
                {"masina"}
        };
    }
    @Test(dataProvider = "searchDP")
    public void positiveSearch(String input){
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.setSearchInput("masina");
        Assert.assertTrue(loginPage.getPositiveSearchMessage().contains("Products Found"));

}


    @DataProvider(name = "searchNegative")
    public Object[][] searchNegative() {
        return new Object[][]{
                {"adjadaxh"}
        };
    }
    @Test(dataProvider = "searchNegative")
    public void searchNegative(String input){
        System.out.println("Open Browser");
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.setSearchInput("xxzxsdsdsd");
        Assert.assertTrue(loginPage.getNegativeSearchMessage().contains("NU S-A GĂSIT NICI UN PRODUS"));

    }


}
