package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By checkBoxTitleName  = By.className("rct-title");
    
    private By outputText = By.id("result");

    // Constructor
    public CheckBoxPage(WebDriver driver) {
    	this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Methods for interacting with elements
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop - (viewPortHeight / 2));", 
            element);
    }

    public void clickOnCheckBox() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxTitleName));
        element.click();
    }
    
    public String getOutputText() {
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(outputText));
    	scrollToElement(element);
    	return element.getText();

    }

}
