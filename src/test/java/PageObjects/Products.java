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

    @FindBy(xpath = " //div[@class='cart-item-details']/a[@class='product-title']")
    private WebElement productName;

    @FindBy(xpath = "//a[@href='https://www.creatoys.ro/cosul-meu/']")
    private WebElement myCart;

    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//child::li")
    private WebElement errorMaxProductInCart;

    @FindBy(xpath = " //a[@href='https://www.creatoys.ro/produs/casuta-de-papusi-cu-mobilier-little-dutch/']")
    private WebElement dollHouseImage;

    @FindBy(xpath = "//tbody/tr/td[5]/div[@class='quantity']/span[2]")
    private WebElement plusButton;

    @FindBy(css = "div[data-type='success']")
    private WebElement produsAdagugatCuSucces;

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

    public void clickonProduct() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=750754']")));
        dollHouse.click();

    }

    public void clickonProductImage() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(presenceOfElementLocated(By.xpath("//a[@href='https://www.creatoys.ro/produs/casuta-de-papusi-cu-mobilier-little-dutch/']")));
        dollHouseImage.click();

    }

    public void clickOnMyCart() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//a[@href='https://www.creatoys.ro/cosul-meu/']")));
        myCart.click();
    }


    public void clickOnPlus() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//td[contains(@class, 'product-quantity')]/div/span[@class='plus']")));
      //  myCart.click();
        driver.findElement(By.xpath("//td[contains(@class, 'product-quantity')]/div/span[@class='plus']")).click();

    }


    public String maxProductInCartMessageError() {

        try {
            return errorMaxProductInCart.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public boolean isDisplayedMaxProductInCartMessageError() {
            try {
                 errorMaxProductInCart.isDisplayed();
                 return true;
            }
            catch (NoSuchElementException ex) {
                return false;
            }
    }

    public boolean isProdusAdaugatCuSucces() {
        try {
            produsAdagugatCuSucces.isDisplayed();
            return true;
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void waitToDisappear() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(produsAdagugatCuSucces));
    }

}
