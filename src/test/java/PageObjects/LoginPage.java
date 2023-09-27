package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;


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
    private WebElement registerPageBtn;

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

//    @FindBy(xpath = "//div[@class='et_element et_b_header-logo align-start mob-align-center et_element-top-level']//a")
//    private WebElement creaLogo;

    @FindBy(xpath = "//span[@class='et-cart-total-inner']/span")
    private WebElement myCart;

    @FindBy(xpath = " //div[@class='cart-item-details']/a[@class='product-title']")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//ul[@class='woocommerce-error']//li/a]")
    private WebElement errorMaxProductInCart;

    @FindBy(xpath = "//*[@id='menu-item-791023']/a")
    private WebElement promotionsPage;

    //div[@class='menu-main-container']/ul[@id='menu-xsmain-menu']/../li[@id='menu-item-791023']/a
//div[@class='menu-main-container']/ul[@id='menu-xsmain-menu']/li[@id='menu-item-791023']/a]

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    public void goToLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(myAccount));
        myAccount.click();
    }

    public void accessRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(registerPageBtn));
        registerPageBtn.click();
    }

    public void goToRegistrationPage() {
        wait.until(ExpectedConditions.visibilityOf(registerPageBtn));
        myAccount.click();
    }

    public String getPassErr() {
        try {
            return passError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String geUsernameErr() {
        try {
            return usernameErr.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void login(String email, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(username));
        username.clear();
        username.sendKeys(email);
        password.clear();
        password.sendKeys(pass);
        login.click();
    }

    public String WelcomeRegister() {

        try {
            return welcomeRegisterMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void goToCreaPage() {
        wait.until(ExpectedConditions.visibilityOf(creaLogo));
        myAccount.click();
    }


    public void goToProducts(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(creaLogo));
        creaLogo.click();
    }

    public String CreaPageText() {

        try {
            return creaLogo.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void clickonProduct() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=750754']")));
        dollHouse.click();

    }

    public void clickOnMyCart() {
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//div[@class='text-center product-details']/span[@class='price']/../a[@href='?add-to-cart=750754']")));
        myCart.click();

    }

    public String productName() {

        try {
            return productName.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }


    public void goToPromotionsPage() {
        wait.until(ExpectedConditions.visibilityOf(promotionsPage));
        promotionsPage.click();
    }


}

    //  public CookiePage goToCookiePage(){
  //      wait.until(ExpectedConditions.visibilityOf(cookieButtonElement));
  //      cookieButtonElement.click();
 //      return new CookiePage(driver);
 //   }



