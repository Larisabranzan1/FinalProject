package PageObjects;

import Tests.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Products extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    Actions actions;

    public Products(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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

    @FindBy(xpath = "//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=750754']")
    private WebElement dollHouse;


    @FindBy(xpath = "//div[@class='et_element et_b_header-logo align-start mob-align-center et_element-top-level']//a")
    private WebElement creaLogo;

    @FindBy(xpath = " //div[@class='cart-item-details']/a")
    private WebElement productName;

    @FindBy(xpath = "//a[@href='https://www.creatoys.ro/cosul-meu/']")
    private WebElement myCart;

    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//child::li")
    private WebElement errorMaxProductInCart;

    @FindBy(xpath = "//a[@href='https://www.creatoys.ro/produs/casuta-de-papusi-cu-mobilier-little-dutch/']")
    private WebElement dollHouseImage;

    @FindBy(xpath = "//tbody/tr/td[5]/div[@class='quantity']/span[2]")
    private WebElement plusButton;

    @FindBy(xpath = "//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=767199']")
    private WebElement carProductAddToCartBtn;

    @FindBy(xpath = "//div[@class='col-md-12 col-sm-12 mob-center']/a")
    private WebElement removeProductsFromBin;

    @FindBy(xpath = "//div[@class='wc-proceed-to-checkout']/a[@href='https://www.creatoys.ro/checkout-2/']")
    private WebElement proceedToCheckOut;

    @FindBy(xpath = "//div[@class='cart-empty empty-cart-block']/h1")
    private WebElement emptyBinMessage;


    public String emptyBinMessage() {

        try {
            return emptyBinMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void removeProductsFromBin() {
        wait.until(elementToBeClickable(removeProductsFromBin));
        removeProductsFromBin.click();
    }

    public void removeFromBinUsingScroll() throws MyCustomException {
        wait.until(ExpectedConditions.visibilityOf(removeProductsFromBin));
        int currentRetry = 0;
        while (currentRetry < 50) {
            try {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
                removeProductsFromBin.click();
                break;
            } catch (MoveTargetOutOfBoundsException | ElementClickInterceptedException e) {
                currentRetry++;
            }
        }
        if (currentRetry >= 50) {
            throw new MyCustomException("Max retry reached");
        }
    }

    public void removeFromBinUsingScroll2() throws MyCustomException {
        wait.until(ExpectedConditions.visibilityOf(removeProductsFromBin));
        int currentRetry = 0;
        while (currentRetry < 50) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", removeProductsFromBin);
                removeProductsFromBin.click();
                break;
            } catch (ElementClickInterceptedException e) {
                currentRetry++;
            }
        }
        if (currentRetry >= 50) {
            throw new MyCustomException("Max retry reached");
        }
    }


    public void goToCheckOut(WebDriver driver) {
        wait.until(elementToBeClickable(proceedToCheckOut));
        proceedToCheckOut.click();
    }



    public void clickonProduct(String productID) {
        String productXpath = "//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=" + productID + "']";
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productXpath)));
        product.click();
    }



    public void clickOnMyCart() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//a[@href='https://www.creatoys.ro/cosul-meu/']")));
        myCart.click();

    }

    public String getCartQTY() {
        WebElement qty = wait.until(presenceOfElementLocated(By.xpath("//input[contains(@id,'quantity')]")));
        return qty.getAttribute("value");
    }

    public void clickOnPlus() {
        wait.until(elementToBeClickable(plusButton));
        plusButton.click();

    }


    public String getDollHouseText() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='cart-item-details']/a")));
        return productName.getText();
    }
}



