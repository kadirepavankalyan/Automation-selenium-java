package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RadioButtonPage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By headerName  = By.className("text-center");
    
    private By YesRadioButton = By.xpath("//*[@id='app']/div/div/div/div[2]/div[2]/div[2]/label");
    
    private By ImpressiveRadioButton = By.xpath("//*[@id='app']/div/div/div/div[2]/div[2]/div[3]/label");
    
    private By outputText = By.className("text-success");

    // Constructor
    public RadioButtonPage(WebDriver driver) {
    	this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods for interacting with elements
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop - (viewPortHeight / 2));", 
            element);
    }
    public String headerText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(headerName));
        return element.getText();
    }
    
    public void clickOnYesRadioButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(YesRadioButton));
        element.click();
    }
    
    public void clickOnImpressiveRadioButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ImpressiveRadioButton));
        element.click()
;    }
    
    public String getOutputText() {
        try {
        	Thread.sleep(2000);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(outputText));
            scrollToElement(element);
            return element.getText();
        } catch (Exception e) {
            System.out.println("An error occurred while fetching the output text: " + e.getMessage());
            return null;
        }
    }
}
