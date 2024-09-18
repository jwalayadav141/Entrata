package entrara;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RunTest{
	public WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\java\\Resource\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.entrata.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//a[@id=\"cookie-accept\"]")).click();
	}
	
	@Test
	public void WatchDemo() throws InterruptedException {
		Thread.sleep(5000);
		WebElement watchDemo = driver.findElement(By.xpath("/html/body/section[1]/div[14]/div[1]/nav/div[5]/a[1]"));
		Thread.sleep(5000);
		watchDemo.click();
		WebElement firstName = driver.findElement(By.xpath("//input[@id=\"FirstName\"]"));
		WebElement lastName = driver.findElement(By.xpath("//input[@id=\"LastName\"]"));
		WebElement email = driver.findElement(By.xpath("//input[@id=\"Email\"]"));
		WebElement companyName = driver.findElement(By.xpath("//input[@id=\"Company\"]"));
		WebElement phoneNumber = driver.findElement(By.xpath("//input[@id=\"Phone\"]"));
		WebElement unitCount = driver.findElement(By.xpath("//select[@id=\"Unit_Count__c\"]"));
		WebElement jobTitle = driver.findElement(By.xpath("//input[@id=\"Title\"]"));
		WebElement iAm = driver.findElement(By.xpath("//select[@id=\"demoRequest\"]"));
		firstName.sendKeys("Jwala");
		lastName.sendKeys("Yadav");
		email.sendKeys("xyz@gmail.com");
		companyName.sendKeys("Entrata");
		phoneNumber.sendKeys("9456322156");
		Select unit = new Select(unitCount);
		unit.selectByVisibleText("1 - 10");
		jobTitle.sendKeys("Software Quality Engineer");
		Select iam=new Select(iAm);
		iam.selectByVisibleText("a Resident");
	}
	
	@Test(enabled=false)
	public void products() {
		WebElement products=driver.findElement(By.xpath("//div[contains(text(),\"Products\")]"));
		Actions action = new Actions(driver);
		action.moveToElement(products).perform();
		WebElement residentPay = driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-7\"]/div/div[2]/div[1]/a[2]"));
//		WebElement residentPay = driver.findElement(By.xpath("//div[@class=\"mega-nav desktop\"]//a[@href=\"/products/residentpay\"]"));
		residentPay.click();
		WebElement header=driver.findElement(By.xpath("//h1[@class=\"product-name space superscript-filter\"]"));
		String productHeader = header.getText();
		Assert.assertEquals(productHeader, "ResidentPay");
		
	}
	@Test(enabled=false)
	public void solutions() {
		
	}
	
	@AfterClass
	public void tearDown() {

	}
}
