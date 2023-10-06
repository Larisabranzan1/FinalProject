package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Promotions {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(xpath = "//div[@class='secondary-menu-wrapper']")
    private WebElement hoverMeButtonElementCategories;

    @FindBy(xpath = "//*[@id='menu-item-706723']/a")
    private WebElement brandsButtonHover;

    @FindBy(xpath = "//*[@id='menu-item-743585']")
    private WebElement selectedDropDownOptionElement;

    @FindBy(xpath = "//*[@id='menu-item-706723']/div/div")
    private List<WebElement> dropDownElements;


    @FindBy(xpath = "//h2[@class='product-title']/a[contains(text(), 'AquaPlay BoatSet')]")
    private WebElement AquaPlayProduct;



    public Promotions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public boolean brandsButton() {
        wait.until(ExpectedConditions.visibilityOf(brandsButtonHover));
        return brandsButtonHover.isDisplayed();
    }

    public List<String> getDropDownOptions() {
        List<String> dropDownOptions = new ArrayList<>();
        for (WebElement dropDownOptionElement : dropDownElements) {
            dropDownOptions.add(dropDownOptionElement.getText());
        }
        return dropDownOptions;
    }

    public void moveToButton() {
        actions.moveToElement(brandsButtonHover).build().perform();
    }



    public void clickOnOption(String option){
        String optionXpath="//div[@class='nav-sublist']//a[contains(text(), '"+option+"')]";
        WebElement optionElement=driver.findElement(By.xpath(optionXpath));
        wait.until(ExpectedConditions.elementToBeClickable(optionElement));
        optionElement.click();
    }

    public String getAquaPlayText() {
        return AquaPlayProduct.getText();
    }
}