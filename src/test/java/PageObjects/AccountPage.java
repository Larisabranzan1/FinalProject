package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='et-element-label inline-block mob-hide' and contains(text(), 'Contul meu')]")
    private WebElement myAccount;


    @FindBy(xpath = "   //div[@class='MyAccount-user-name']/following-sibling::*")
    private WebElement personalAccount;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public String getName(){
        wait.until(ExpectedConditions.visibilityOf(myAccount));
        return myAccount.getText();
    }

    public String getNamePersAccount() {
        wait.until(ExpectedConditions.visibilityOf(personalAccount));
        return personalAccount.getText();
    }
}
