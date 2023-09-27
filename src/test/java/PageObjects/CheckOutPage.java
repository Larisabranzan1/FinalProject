package PageObjects;

import Tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckOutPage extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    Actions actions;
    Products products;
    CheckOutPage checkOutPage;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//*[@id='billing_first_name']")
    private WebElement firstNameBilling;


    @FindBy(xpath = "//*[@id='billing_last_name']")
    private WebElement lastNameBilling;

    @FindBy(xpath = "//*[@id='billing_phone']")
    private WebElement billingPhone;

    @FindBy(xpath = "//*[@id='billing_email']")
    private WebElement billingEmail;

    @FindBy(xpath = "//*[@id='billing_address_1']")
    private WebElement billingStreetAddress;

        @FindBy(xpath = "//span[@class='select2-results']/ul")
        private List<WebElement>  billingState;


    //*[@id='select2-billing_state-container'] = what was before in Billing State
    ////*[@id='select2-billing_state-results']

    @FindBy(xpath = "//*[@id='select2-billing_city-container']")
    private WebElement billingStateCity;

    @FindBy(xpath = "//*[@id='billing_postcode']")
    private WebElement postCode;

    @FindBy(xpath = "//*[@id='place_order']")
    private WebElement placeOrder;

    @FindBy(xpath = "//div[@class='woocommerce-form-coupon-toggle']/div/a")
    private WebElement cupon;

    @FindBy(xpath = "//span[@class='woocommerce-terms-and-conditions-checkbox-text']")
    private WebElement termsAndConditions;

    @FindBy(xpath = "//div[@class='woocommerce-billing-fields']/h3/span")
    private WebElement billingInfo;





    public String billingInfo() {
        wait.until(ExpectedConditions.visibilityOf(billingInfo));
        return billingInfo.getText();
    }



    public void enterTextFirstName(String firstName) {
        firstNameBilling.clear();
        firstNameBilling.sendKeys(firstName);
    }

    public void enterTextLastName(String lastName) {
        lastNameBilling.clear();
        lastNameBilling.sendKeys(lastName);
    }



    public void enterTextPhone(String phoneNumber) {
        billingPhone.clear();
        billingPhone.sendKeys(phoneNumber);
    }

    public void enterEmail(String email) {
        billingEmail.clear();
        billingEmail.sendKeys(email);
    }

    public List<String> getDropDownOptions()  {
        List<WebElement> listItems = driver.findElements(billingState);
        String option = "Bucuresti";
        for (WebElement listItem : listItems) {
            String text = listItem.getText();
            if (text.equals("Bucuresti")) {
                listItem.click();
                break; // Exit the loop after clicking the desired option
            }
        }
    }


}
