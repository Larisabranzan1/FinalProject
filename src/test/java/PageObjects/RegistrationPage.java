package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class RegistrationPage  {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    Actions actions;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPass;

    @FindBy(xpath = "//*[@id='privacy_policy_reg']/..")
    private WebElement acceptPolicy;


    @FindBy(xpath = "//button[@name='register']")
    private WebElement registerPageBtn;

    @FindBy(xpath = "//span[@class='et-element-label inline-block mob-hide' and contains(text(), 'Contul meu')]")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement registerErr;

    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement dataProcessingError;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//h3//span[contains(text(), 'Bine ai venit')]")
    private WebElement welcomeRegisterMessage;



    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }


    public void register(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(regEmail));
        regEmail.clear();
        regEmail.sendKeys(username);
       regPass.clear();
        regPass.sendKeys(password);
        registerPageBtn.click();
    }

    public String getName(){
        wait.until(ExpectedConditions.visibilityOf(registerPageBtn));
        return registerPageBtn.getText();
    }

    public String dataProcessingError(){
        wait.until(ExpectedConditions.visibilityOf(dataProcessingError));
        return dataProcessingError.getText();
    }



    public void clickAcceptTerms(){
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.xpath("//*[@id='privacy_policy_reg']/..")));
        clickAction.moveToElement(acceptPolicy);
        acceptPolicy.click();
    }



    public String WelcomeRegister(){
        wait.until(ExpectedConditions.visibilityOf(welcomeRegisterMessage));
        return welcomeRegisterMessage.getText();
    }

    public String getRegisterErr() {

        try {
            wait.until(ExpectedConditions.visibilityOf(registerErr));
            return registerErr.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }




    public void randomRegister(String newUsername, String newPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(regEmail));
        regEmail.clear();
        int number = new Random().nextInt(1000);
         newUsername = "user" + Integer.toString(number) + "@creatoys.ro";
        regEmail.sendKeys(newUsername);
        regPass.clear();
        regPass.sendKeys(newPassword);
        registerPageBtn.click();
    }



}
