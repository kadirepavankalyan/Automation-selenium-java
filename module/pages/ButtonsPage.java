package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By doubleClickBtn  = By.id("doubleClickBtn");
    
    private By doubleClickMsg = By.id("doubleClickMessage");
    
    private By rightClickBtn  = By.id("rightClickBtn");
    
    private By rightClickMsg = By.id("rightClickMessage");

    // Constructor
    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initializing wait with a timeout of 10 seconds
    }

    // Methods for interacting with elements
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop - (viewPortHeight / 2));", 
            element);
    }

    public void clickOnDoubleClickButton() {
        WebElement doubleClickMe = wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickBtn));
        scrollToElement(doubleClickMe);
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickMe).perform();
    }
    
    public String getDoubleClickMessage() {
    	 WebElement doubleClickMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickMsg));
         scrollToElement(doubleClickMessage);
    	 return doubleClickMessage.getText();
    }
    
    public void clickOnRightClickButton() {
        WebElement rightClickMe = wait.until(ExpectedConditions.visibilityOfElementLocated(rightClickBtn));
        scrollToElement(rightClickMe);
        Actions action = new Actions(driver);
        action.contextClick(rightClickMe).perform();
    }
    
    public String getRightClickMessage() {
    	 WebElement rightClickMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(rightClickMsg));
         scrollToElement(rightClickMessage);
    	 return rightClickMessage.getText();
    }
}
