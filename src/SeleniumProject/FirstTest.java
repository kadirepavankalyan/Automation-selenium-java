package SeleniumProject;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void openGoogle() {
        driver.get("https://demoqa.com/text-box");
        System.out.println("Title: " + driver.getTitle());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 450);");

        WebElement userName = driver.findElement(By.id("userName"));
        userName.sendKeys("Pavan");

        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("Pavan@dispostable.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("2-4, Bangalore, 560068");

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys("2-4, Hyderabad, 568829");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Explicit wait after clicking the submit button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        System.out.println("Output Text: " + output.getText());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
