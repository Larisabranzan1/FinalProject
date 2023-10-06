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
    Actions actions;



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

    @FindBy(xpath = "//span[@class='et-cart-total-inner']/span")
    private WebElement myCart;

    @FindBy(xpath = " //div[@class='cart-item-details']/a[@class='product-title']")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='woocommerce-notices-wrapper']//ul[@class='woocommerce-error']//li/a]")
    private WebElement errorMaxProductInCart;

    @FindBy(xpath = "//*[@id='menu-item-791023']/a")
    private WebElement promotionsPage;

    @FindBy(xpath="//div[@class='input-row flex align-items-center ']/input[@class='form-control']")
    private WebElement searchInput;

    @FindBy(xpath="//h2[@class='products-title']//span[2]")
    private WebElement positiveSearchMessage;

    @FindBy(xpath="//div[@class='empty-category-block']/h2")
    private WebElement negativeSearch;




    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    public void goToLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(myAccount));
        myAccount.click();
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


    public void goToProducts(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(creaLogo));
        creaLogo.click();
    }



    public void goToPromotionsPage() {
        wait.until(ExpectedConditions.visibilityOf(promotionsPage));
        promotionsPage.click();
    }

    public void setSearchInput(String input){
        actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(input);
        actions.sendKeys(Keys.ENTER).click().build().perform();
    }

    public String getPositiveSearchMessage() {
        try{
            System.out.println(positiveSearchMessage.getText());
            return positiveSearchMessage.getText();
        } catch(NoSuchElementException ex) {
            return "";
        }
    }

    public String getNegativeSearchMessage() {
        try{
            System.out.println(negativeSearch.getText());
            return negativeSearch.getText();
        } catch(NoSuchElementException ex) {
            return "";
        }
    }

}




