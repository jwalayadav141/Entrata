package entrara;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseTest.BaseClass;

public class RunTest extends BaseClass{
	// Define explicit wait
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@Test(priority = 0, enabled = true)
	public void products() {
		// click on resident pay which appears after hover on product from menu bar
		WebElement products = driver.findElement(By.xpath("//div[contains(text(),\"Products\")]"));
		Actions action = new Actions(driver);
		action.moveToElement(products).perform();
		WebElement residentPay = driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-7\"]/div/div[2]/div[1]/a[2]"));
		residentPay.click();
		WebElement header = driver.findElement(By.xpath("//h1[@class=\"product-name space superscript-filter\"]"));
		String productHeader = header.getText();
		// verifying the header is expected as actual or not with help oof assertEquals
		Assert.assertEquals(productHeader, "ResidentPay");

	}
	
	@Test(priority = 1, enabled = true)
	public void summit() throws InterruptedException {
		WebElement summit = driver.findElement(By.xpath("(//a[contains(@href,\"summit\")])[1]"));
		wait.until(ExpectedConditions.visibilityOf(summit));
		summit.click();

		// after click on summit, it's open another window, for that use window handling
		// store parent window address for again back to main window
		String parent = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> I = window.iterator();
		while (I.hasNext()) {
			String summitWindow = I.next();
			if (!parent.equals(summitWindow)) {
				driver.switchTo().window(summitWindow);
			}
		}

		WebElement overview = driver.findElement(By.xpath("//a[contains(text(),\"Overview\")]"));
		WebElement summitDate = driver.findElement(By.xpath("//div[@class=\"summit-dates\"]"));
		WebElement registerNow = driver.findElement(By.xpath("(//a[@href=\"https://cvent.me/mByGQA\"])[3]"));
		wait.until(ExpectedConditions.visibilityOf(overview));
		// verifying the overview text is visible after open summit page
		boolean view = overview.isDisplayed();
		Assert.assertTrue(view);
		// verifying the summit date is visible on summit page using assertTrue
		boolean summitD = summitDate.isDisplayed();
		Assert.assertTrue(summitD);

		wait.until(ExpectedConditions.elementToBeClickable(registerNow));
		registerNow.click();
		WebElement registerNowHeader = driver.findElement(By.xpath("//div[contains(text(),\"Event Registration\")]"));
		wait.until(ExpectedConditions.visibilityOf(registerNowHeader));
		String registerNowHeaderText = registerNowHeader.getText();
		Assert.assertEquals(registerNowHeaderText, "Event Registration");

		driver.switchTo().window(parent);
	}
	
	@Test(priority = 2, enabled = true)
	public void WatchDemo() throws InterruptedException {
		// open the url for watch demo and fill all details without click on submit
		WebElement watchDemo = driver.findElement(By.xpath(
				"//div[contains(text(),\"Watch Demo\")]/ancestor::div[contains(@class,\"nav-button\")]//a[contains(@href,\"go.entrata.com/watch-demo\")]"));
		wait.until(ExpectedConditions.elementToBeClickable(watchDemo));
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
		email.sendKeys("jwalayadav@gmail.com");
		companyName.sendKeys("Entrata");
		phoneNumber.sendKeys("9456322156");
		// select unit from dropdown
		Select unit = new Select(unitCount);
		unit.selectByVisibleText("1 - 10");
		jobTitle.sendKeys("Software Quality Engineer");
		Select iam = new Select(iAm);
		iam.selectByVisibleText("a Resident");
	}

}
