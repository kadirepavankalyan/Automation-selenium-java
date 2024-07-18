package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class fileUploadDownloadPage {
	 
	 private WebDriver driver;
	    private WebDriverWait wait;

	    // Locators
	    private By checkBoxTitleName  = By.className("rct-title");
	    
	    // Constructor
	    public void fileUploadDowloadPage(WebDriver driver) {
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
	    
	    public void clickOnCheckBox() {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxTitleName));
	        scrollToElement(element);
	        element.click();
	    }

}
