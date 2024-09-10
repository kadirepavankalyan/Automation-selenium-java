package Tests;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CheckBoxPage;
import pages.RadioButtonPage;
import pages.TextBoxPage;
import pages.WebTablesPage;
import pages.ButtonsPage;
import pages.AlertsPage;

public class LoginTest {
    private WebDriver driver;
    private String downloadPath = "C:\\JavaSelenium\\downloads";
    private String filePath = "C:\\JavaSelenium\\uploads\\sampleFile.jpeg";
    
    private TextBoxPage textBoxPage;
    private CheckBoxPage checkBoxPage;
    private RadioButtonPage radioButtonPage;
    private WebTablesPage webTablesPage;
    private ButtonsPage buttonsPage;
    private AlertsPage alertsPage;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver-win64\\chromedriver.exe");
        
        // Set download directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadPath);
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
        textBoxPage = new TextBoxPage(driver);
        checkBoxPage = new CheckBoxPage(driver);
        radioButtonPage = new RadioButtonPage(driver);
        webTablesPage = new WebTablesPage(driver);
        buttonsPage = new ButtonsPage(driver);
        alertsPage = new AlertsPage(driver);
        
        driver.manage().window().maximize();
    }

    @Test(priority=1)
    public void fillTextBoxForm() {
        driver.get("https://demoqa.com/text-box");
        Assert.assertEquals(driver.getTitle(), "DEMOQA");

        textBoxPage.enterUserName("Pavan");
        textBoxPage.enterUserEmail("Pavan@dispostable.com");
        textBoxPage.enterCurrentAddress("2-4, Bangalore, 560068");
        textBoxPage.enterPermanentAddress("2-4, Hyderabad, 568829");
        textBoxPage.clickSubmitButton();
        
        String outputText = textBoxPage.getOutputText();
        Assert.assertTrue(outputText.contains("Pavan"));
        Assert.assertTrue(outputText.contains("Pavan@dispostable.com"));
        System.out.println("Output Text after clicking on submit with valid details: " + outputText);
    }
    
    @Test(priority=2)
    public void checkBoxForm() {
        driver.get("https://demoqa.com/checkbox");
        
        checkBoxPage.clickOnCheckBox();
        
        String outputText = checkBoxPage.getOutputText();
        Assert.assertNotNull(outputText);
        System.out.println("Output Text after checkbox selection: " + outputText);
    }

    @Test(priority=3)
    public void radioButtonForm() {
        driver.get("https://demoqa.com/radio-button");
        
        String header = radioButtonPage.headerText();
        Assert.assertEquals(header, "Radio Button");
        System.out.println("Header: " + header);
        
        radioButtonPage.clickOnYesRadioButton();
        String actualOutput1 = radioButtonPage.getOutputText();
        Assert.assertEquals(actualOutput1, "Yes");
        System.out.println("OutputText: " + actualOutput1);
               
        radioButtonPage.clickOnImpressiveRadioButton();
        String actualOutput2 = radioButtonPage.getOutputText();
        Assert.assertEquals(actualOutput2, "Impressive");
        System.out.println("OutputText: " + actualOutput2);
    }
    
	 private void clearDownloadDirectory() {
	    File downloadDir = new File(downloadPath);
	    if (downloadDir.exists() && downloadDir.isDirectory()) {
	        for (File file : downloadDir.listFiles()) {
	            if (!file.isDirectory()) {
	            	file.delete();
	             }
	         }
	    }
	}
	 
    @Test(priority=4)
    public void fileDownload() {
    	clearDownloadDirectory();   	
        driver.get("https://demoqa.com/upload-download");

        WebElement downloadLink = driver.findElement(By.id("downloadButton"));
        downloadLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        File downloadDir1 = new File(downloadPath);
        
        boolean fileDownloaded = wait.until(driver -> {
            File[] files = downloadDir1.listFiles((dir, name) -> name.matches("sampleFile( \\(\\d+\\))?\\.jpeg"));
            return files != null && files.length > 0;
        });
        System.out.println("File downloaded successfully");
        Assert.assertTrue(fileDownloaded, "File not downloaded successfully");
    }
    
    @Test(priority=5)
    public void fileUpload() { 
    	driver.get("https://demoqa.com/upload-download");

    	WebElement uploadFile = driver.findElement(By.id("uploadFile"));
    	uploadFile.sendKeys(filePath);
    	
    	WebElement UploadedFilePath = driver.findElement(By.id("uploadedFilePath"));
    	System.out.println(UploadedFilePath.getText());
    	System.out.println("File uploaded successfully");
    	Assert.assertEquals(UploadedFilePath.getText(), "C:\\fakepath\\sampleFile.jpeg");
    }
    
    @Test(priority=6)
    public void webTablesForm() {
    	driver.get("https://demoqa.com/webtables");
    	
    	int[] columnIndices = {1, 2, 3, 4, 5, 6}; // Adjust these based on the actual table structure
        String[] columnNames = {"First Name", "Last Name", "Age", "Email", "Salary", "Department"};

        for (int k = 0; k < columnIndices.length; k++) {
            List<String> columnData = webTablesPage.getColumnData(columnIndices[k]);
            System.out.println(columnNames[k] + ": " + columnData);
        }
     }
    
    @Test(priority=7)
    public void buttonsPage() {
    	 driver.get("https://demoqa.com/buttons");
         buttonsPage.clickOnDoubleClickButton();
         String expectedDoubleClickMsg = buttonsPage.getDoubleClickMessage(); 
         System.out.println(expectedDoubleClickMsg);
         Assert.assertEquals(expectedDoubleClickMsg, "You have done a double click");
         
         buttonsPage.clickOnRightClickButton();
         String expectedRightClickMsg = buttonsPage.getRightClickMessage();
         System.out.println(expectedRightClickMsg);
         Assert.assertEquals(expectedRightClickMsg, "You have done a right click");
    }
    
    @Test(priority=8)
    public void alertsPage() {
    	 driver.get("https://demoqa.com/alerts");
    	 alertsPage.clickOnAlertButton();
    	 Alert simpleAlert = driver.switchTo().alert();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    	 simpleAlert.accept();
    	 String expectedResult = alertsPage.getConfirmResult();
    	 System.out.println(expectedResult);
    	 Assert.assertEquals(expectedResult, "You selected Ok");
    }
    
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
