package SeleniumProject;

public class App {

	public static void main(String[] args) {
		/* TODO Auto-generated method stub
		System.out.println("Script starts here");
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		
		options.addArguments("--disable-gpu");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--headless");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/text-box");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 350);");
				
		WebElement userName = driver.findElement(By.id("userName"));
		userName.sendKeys("Pavan");		
		
		WebElement userEmail = driver.findElement(By.id("userEmail"));
		userEmail.sendKeys("Pavan@dispostable.com");
		System.out.println("Script ends here");

		driver.quit();
		} */
		    FirstTest test = new FirstTest();
	        test.setUp();
	        test.openGoogle();
	        test.tearDown();
	    }
}
