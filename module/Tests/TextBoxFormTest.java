package Tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TextBoxPage;
import utils.ExcelUtils;

public class TextBoxFormTest {

    private WebDriver driver;
    private TextBoxPage textBoxPage;

    @BeforeTest
    public void setUp() {
        // Initialize WebDriver and navigate to the page
        System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        textBoxPage = new TextBoxPage(driver);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void fillTextBoxForm() throws IOException {
        // Set the Excel file and sheet
        String excelFilePath = "C:\\JavaSelenium\\uploads\\excel\\TestData.xlsx";
        String sheetName = "TextBoxForm";
        ExcelUtils.setExcelFile(excelFilePath, sheetName);

        // Read data from Excel
        String userName = ExcelUtils.getCellData(1, 0); // Assuming first row is header
        String userEmail = ExcelUtils.getCellData(1, 1);
        String currentAddress = ExcelUtils.getCellData(1, 2);
        String permanentAddress = ExcelUtils.getCellData(1, 3);

        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getTitle(), "DEMOQA");

        textBoxPage.enterUserName(userName);
        textBoxPage.enterUserEmail(userEmail);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.clickSubmitButton();

        String outputText = textBoxPage.getOutputText();
        Assert.assertTrue(outputText.contains(userName));
        Assert.assertTrue(outputText.contains(userEmail));
        System.out.println("Output Text after clicking on submit with valid details: " + outputText);

        // Close the Excel file
        ExcelUtils.closeExcelFile();
    }

    @AfterTest
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
