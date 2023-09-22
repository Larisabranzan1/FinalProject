package PageObjects;

import Tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Products extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    Actions actions;

    public Products(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    @FindBy(id = "reg_email")
    private WebElement registerPageBtn;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPass;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//h3//span[contains(text(), 'Bine ai venit')]")
    private WebElement welcomeRegisterMessage;

    @FindBy(id = "privacy_policy_reg")
    private WebElement acceptPolicy;


    @FindBy(xpath = "//button[@name='register']")
    private WebElement registerPageBtnn;

    @FindBy(xpath = "//span[@class='et-element-label inline-block mob-hide' and contains(text(), 'Contul meu')]")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement usernameErr;


    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement passError;

    @FindBy(xpath = "//*[@name='login']")
    private WebElement login;


    @FindBy(xpath = "//div[@class='et_element et_b_header-logo align-start mob-align-center et_element-top-level']//a")
    private WebElement creaLogo;

    @FindBy(xpath = " //div[@class='cart-item-details']/a[@class='product-title']")
    private WebElement productName;

    @FindBy(xpath = "//span[@class='et-cart-total-inner']")
    private WebElement myCart;

    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//child::li")
    private WebElement errorMaxProductInCart;

    @FindBy(xpath = " //a[@href='https://www.creatoys.ro/produs/casuta-de-papusi-cu-mobilier-little-dutch/']")
    private WebElement dollHouseImage;

    @FindBy(xpath = "//td[contains(@class,'product-quantity')]/div/span[@class='plus']")
    private WebElement plusButton;

//tbody/tr/td[5]/div[@class='quantity']/span[2]
//span[@class='et-cart-total-inner']/span


    public String CreaPageText() {

        try {
            return creaLogo.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }


    public void goToCreaPage() {
        wait.until(ExpectedConditions.visibilityOf(creaLogo));
        myAccount.click();
    }


    public void goToCreaLogo(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(creaLogo));
        creaLogo.click();
    }


    public String productName() {

        try {
            return productName.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void clickonProduct(String productID) {
        String productXpath = "//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=" + productID + "']";
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productXpath)));
        product.click();
    }

    public void clickonProductImage() {
        wait.until(presenceOfElementLocated(By.xpath("//a[@href='https://www.creatoys.ro/produs/casuta-de-papusi-cu-mobilier-little-dutch/']")));
        dollHouseImage.click();

    }

    public void clickOnMyCart() {
        wait.until(elementToBeClickable(myCart));
        myCart.click();
    }

    public String getCartQTY() {
        WebElement qty = wait.until(presenceOfElementLocated(By.xpath("//input[contains(@id,'quantity')]")));
        return qty.getAttribute("value");
    }

    public void clickOnPlus() {
        wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        plusButton.click();

    }


    public String maxProductInCartMessageError() {

        try {
            return errorMaxProductInCart.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

}
