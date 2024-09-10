package Tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TextBoxPage;
import utils.ExcelUtils;

public class TextBoxFormTest {

    private WebDriver driver;
    private TextBoxPage textBoxPage;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        textBoxPage = new TextBoxPage(driver);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "LoginData")
    public void fillTextBoxForm(String userName, String userEmail, String currentAddress, String permanentAddress) throws IOException {
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
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = ".\\uploads\\excel\\TestData.xlsx";
        ExcelUtils excelUtils = new ExcelUtils(path);

        int totalRows = excelUtils.getRowCount("Sheet1");
        int totalCols = excelUtils.getCellCount("Sheet1", 1);

        String[][] loginData = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = excelUtils.getCellData("Sheet1", i, j);
            }
        }

        return loginData;
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
