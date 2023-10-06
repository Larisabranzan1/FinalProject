package Tests;

import PageObjects.Promotions;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ActionsTest extends BaseTest {
    LoginPage loginPage;
    Promotions promotions;

    @Test
    public void hoverTest() {
        loginPage = new LoginPage(driver);
        loginPage.goToPromotionsPage();
        promotions = new Promotions(driver);
        Assert.assertTrue(promotions.brandsButton(),
                "The Hover Me button is not displayed");
        promotions.moveToButton();
        List<String> dropDownOptions = promotions.getDropDownOptions();
        for (String dropDownOption : dropDownOptions) {
            System.out.println(dropDownOption);
//            String emptyString = "";
//            String emptyString2 = null;
            Assert.assertNotEquals("", dropDownOption, "The drop down option is empty");
//            Assert.assertFalse(dropDownOption.length() == 0);
        }
    }


    @DataProvider(name = "clickFirstDropDownOption")
    public Object[][] RemoveFromCart() {
        return new Object[][]{
                {"chrome", "AquaPlay BoatSet set vehicule, 7 piese"},

        };
    }

    @Test(dataProvider = "clickFirstDropDownOption")
    public void clickFirstDropDownOption(String browser, String message) {
        loginPage = new LoginPage(driver);
        loginPage.goToPromotionsPage();
        promotions = new Promotions(driver);
        Assert.assertTrue(promotions.brandsButton(),
                "The Brands Button is not displayed");
        promotions.moveToButton();
        promotions.clickOnOption("Aquaplay");
        Assert.assertEquals(promotions.getAquaPlayText(), message);
    }
}



