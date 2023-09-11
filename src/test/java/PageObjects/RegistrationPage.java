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

    @FindBy(id = "privacy_policy_reg")
    private WebElement acceptPolicy;


    @FindBy(xpath = "//button[@name='register']")
    private WebElement registerPageBtn;

    @FindBy(xpath = "//span[@class='et-element-label inline-block mob-hide' and contains(text(), 'Contul meu')]")
    private WebElement myAccount;

    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement  usernameErr;


    @FindBy(xpath = "//*[@class='woocommerce-error']//child::li")
    private WebElement passError;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }




    public void usernameInputRegistration(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(regEmail));
        regEmail.clear();
        regEmail.sendKeys(username);
    }

        public void passwordInputRegistration(String password){
            wait.until(ExpectedConditions.elementToBeClickable(regPass));
            regPass.clear();
            regPass.sendKeys(password);
        }

    public String getName(){
        wait.until(ExpectedConditions.visibilityOf(registerPageBtn));
        return registerPageBtn.getText();
    }
    public void accessRegistrationPage(){
        wait.until(ExpectedConditions.elementToBeClickable(registerPageBtn));
        registerPageBtn.click();
    }

    public void clickTermsCheckboxUsingActionsScroll() throws MyCustomException {
        wait.until(ExpectedConditions.visibilityOf(acceptPolicy));
        int currentRetry = 0;
        while (currentRetry < 50) {
            try {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
                acceptPolicy.click();
                break;
            } catch (MoveTargetOutOfBoundsException | ElementClickInterceptedException e) {
                currentRetry++;
            }
        }
        if (currentRetry >= 50) {
            throw new MyCustomException("Max retry reached");
        }
    }

    public void unclickTermsCheckboxUsingActionsScroll() throws MyCustomException {
        wait.until(ExpectedConditions.visibilityOf(acceptPolicy));
        int currentRetry = 0;
        while (currentRetry < 50) {
            try {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).keyUp(Keys.CONTROL).build().perform();
                if (acceptPolicy.isSelected()) {
                    acceptPolicy.click();
                }
                break;
            } catch (MoveTargetOutOfBoundsException | ElementClickInterceptedException e) {
                currentRetry++;
            }
        }
        if (currentRetry >= 50) {
            throw new MyCustomException("Max retry reached");
        }
    }

    public boolean isCheckBoxSelected(){
        return acceptPolicy.isSelected();
    }

    public void clickRegisterBtn(){
        Actions clickAction = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(presenceOfElementLocated(By.id("reg_email")));
        clickAction.moveToElement(registerPageBtn);
        registerPageBtn.sendKeys(Keys.ENTER);
        registerPageBtn.click();
    }

    public String usernameErr() {
        try {
            return regEmail.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String passError() {
        try {
            return regPass.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }



}
