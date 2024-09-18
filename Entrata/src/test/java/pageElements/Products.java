package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Products {
	
	WebDriver driver;
	By signIn = By.xpath("//a[contains(text(),\"Sign In\")]");
	
	public void products()
	{
		driver.findElement(signIn).click();
	}

}
