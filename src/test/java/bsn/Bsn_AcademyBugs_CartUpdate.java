package bsn;

import base.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Bsn_AcademyBugs_CartUpdate extends Browsers {

	WebDriver driver;
	int quantity = 10;

	public Bsn_AcademyBugs_CartUpdate(WebDriver Drdriver) {
		driver = Drdriver;
	}

	public void Run() {
		// Add item and go to checkout view
		driver.findElement(By.xpath("//*[contains(text(),\"Accept cookies\")]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ec_add_to_cart_27")));
		driver.findElement(By.id("ec_add_to_cart_27")).click();
		delay(7);
		driver.findElement(By.xpath("//a[@id='ec_added_to_cart_27' and contains(text(), \"CHECKOUT NOW\")]")).click();
		
		/*Update quantity to 10 items, price of the item should be 10x, therefore, if the item is $15.14,
		total amount for only the items should be $151.40*/

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ec_quantity']")));
		driver.findElement(By.xpath("//input[@class='ec_quantity']")).clear();
		driver.findElement(By.xpath("//input[@class='ec_quantity']")).sendKeys(Integer.toString(quantity));
		driver.findElement(By.xpath("//div[@class='ec_cartitem_update_button']")).click();

		//Closing popups from the page indicating is a bug
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='popmake-4406']//child::button")));
		driver.findElement(By.xpath("//div[@id='popmake-4406']//child::button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='popmake-4393']//child::button")));
		driver.findElement(By.xpath("//div[@id='popmake-4393']//child::button")).click();

		// Comparing total price
		String price = driver.findElement(By.xpath("//td[@class='ec_cartitem_price']")).getText();
		String totalPrice = driver.findElement(By.xpath("//td[@class='ec_cartitem_total']")).getText();
		double nPrice = PriceConvt(price);
		double nTotal = PriceConvt(totalPrice);
		Assert.assertEquals((nPrice * quantity), nTotal);
	}

	// Only works with strings that start with a $ symbol
	private double PriceConvt(String str) {
		String n1 = str.substring(1);
		double number = Double.parseDouble(n1);
		return number;
	}
}