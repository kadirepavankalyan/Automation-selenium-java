package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By alertButton  = By.id("confirmButton");
    
    private By confirmResultMsg = By.id("confirmResult");
    
     // Constructor
    public AlertsPage(WebDriver driver) {
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

    public void clickOnAlertButton() {
        WebElement alertButtonCta = wait.until(ExpectedConditions.visibilityOfElementLocated(alertButton));
        scrollToElement(alertButtonCta);
        alertButtonCta.click();

    }
    
    public String getConfirmResult() {
    	 WebElement confirmResult = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmResultMsg));
         scrollToElement(confirmResult);
         return confirmResult.getText();
    }
}
