package PageObjects;

import Tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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


    @FindBy(xpath = "//id=select2-billing_state-container")
    private WebElement hoverStateButton;


    @FindBy(xpath = "//*[@id='billing_last_name']")
    private WebElement lastNameBilling;

    @FindBy(xpath = "//*[@id='billing_phone']")
    private WebElement billingPhone;

    @FindBy(xpath = "//*[@id='billing_email']")
    private WebElement billingEmail;

    @FindBy(xpath = "//*[@id='billing_address_1']")
    private WebElement billingStreetAddress;

    @FindBy(xpath = "//*[@id='select2-billing_state-container']") //Judet
    private WebElement  billingState;

    @FindBy(xpath = "//*[@id='select2-billing_city-container']") //Localitate
    private WebElement billingStateCity;

    @FindBy(xpath = "//*[@id='select2-billing_country-container']") //Tara
    private WebElement billingCountry;

    @FindBy(xpath = "//*[@id='billing_postcode']") //CodPostal
    private WebElement postCode;

    @FindBy(xpath = "//span[@class='woocommerce-input-wrapper']//input[@id='billing_address_1']") //Street
    private WebElement streetAdrress;

    @FindBy(xpath = "//*[@id='place_order']")
    private WebElement placeOrder;

    @FindBy(xpath = "//div[@class='woocommerce-form-coupon-toggle']/div/a")
    private WebElement cupon;

    @FindBy(xpath = "//label[contains(@class, 'woocommerce-form__label') and contains(@class, 'woocommerce-form__label-for-checkbox')]/input[@id='terms']")
    private WebElement termsAndConditions;

    @FindBy(xpath = "//div[@class='woocommerce-billing-fields']/h3/span")
    private WebElement billingInfo;

    @FindBy(xpath = " //button[@type='submit' and contains(text(), 'Plasează comanda')]")
    private WebElement submitOrder;

    @FindBy(xpath = "//div[@class='order-review']/h3/span")
    private WebElement myOrder;

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


    public void enterPostal(String post) {
       postCode.clear();
       postCode.sendKeys(post);
    }

    public void enterStreet(String street) {
        streetAdrress.clear();
        streetAdrress.sendKeys(street);
    }

    public void clickOnField(String option){
        String optionXpath="//li[contains(text(), '"+option+"')]";
        WebElement optionElement=driver.findElement(By.xpath(optionXpath));
        wait.until(ExpectedConditions.elementToBeClickable(optionElement));
        optionElement.click();
    }


    public void moveToCityAndClick() {
        wait.until(elementToBeClickable(billingStateCity));
        billingStateCity.click();

    }


    public void moveToStateAndClick() {
        wait.until(elementToBeClickable(billingState));
        billingState.click();
    }

    public void moveToCountryAndClick() {
        wait.until(elementToBeClickable(billingCountry));
        billingCountry.click();
    }



    public void clickOnSubmitOrder() {
        wait.until(elementToBeClickable(submitOrder));
        submitOrder.click();
    }

    public String myOrder() {
        wait.until(ExpectedConditions.visibilityOf(myOrder));
        return myOrder.getText();
    }

}




