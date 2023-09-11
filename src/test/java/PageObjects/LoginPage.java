package PageObjects;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


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

}


    //  public CookiePage goToCookiePage(){
  //      wait.until(ExpectedConditions.visibilityOf(cookieButtonElement));
  //      cookieButtonElement.click();
 //      return new CookiePage(driver);
 //   }



