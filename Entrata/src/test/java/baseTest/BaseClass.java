package baseTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;

	@BeforeClass
	public void setUp() {		
		WebDriverManager.chromedriver().browserVersion("77.0.3865.40").setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); 
		options.addArguments("enable-automation"); 
		options.addArguments("--no-sandbox"); 
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation"); 
		options.addArguments("--disable-gpu"); 
		driver = new ChromeDriver(options); 
		
		// Launch URL 
		driver.get("https://www.entrata.com/");
		
		// Maximize the window
		driver.manage().window().maximize();
		
		//Set Implicit wait which will applicable for whole class
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Accept the cookies present on home page 
		driver.findElement(By.xpath("//a[@id=\"cookie-accept\"]")).click();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
