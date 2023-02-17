package bsn;

import base.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Bsn_AcademyBugs_CheckoutProcess extends Browsers {

	WebDriver driver;

	// Credentials
	public String username;
	public String email = "marcelo@fake.com";
	public String password;
	public String name = "marcelo";
	public String address = "ma";
	public String city = "m";
	public String zipcode = "6758";
	public String phone = "8111111111";

	public Bsn_AcademyBugs_CheckoutProcess(WebDriver Drdriver) {
		driver = Drdriver;
	}

	public void Run() {
		// Add an Item
		delay(4);
		driver.findElement(By.xpath("//*[contains(text(),\"Accept cookies\")]")).click();
		driver.findElement(By.xpath("//div[@id='ec_product_image_effect_flamingo-tshirt']//a[@class='ec_image_link_cover']")).click();
		driver.findElement(By.xpath("//input[@value='ADD TO CART']")).click();

		// CheckOut
		driver.findElement(By.xpath("//a[normalize-space()='Checkout']")).click();

		// Billing information
		Select drpCountry = new Select(driver.findElement(By.id("ec_cart_billing_country")));
		drpCountry.selectByVisibleText("Mexico");
		driver.findElement(By.id("ec_cart_billing_first_name")).sendKeys(name);
		driver.findElement(By.id("ec_cart_billing_last_name")).sendKeys(name);
		driver.findElement(By.id("ec_cart_billing_address")).sendKeys(address);
		driver.findElement(By.id("ec_cart_billing_city")).sendKeys(city);
		driver.findElement(By.id("ec_cart_billing_zip")).sendKeys(zipcode);
		driver.findElement(By.id("ec_cart_billing_phone")).sendKeys(phone);
		driver.findElement(By.id("ec_contact_email")).sendKeys(email);
		driver.findElement(By.id("ec_contact_email_retype")).sendKeys(email);
		driver.findElement(By.xpath("//div[@class='ec_cart_button_row ec_show_two_column_only']//input[@value='CONTINUE TO SHIPPING']")).click();

		// Payment
		driver.findElement(By.xpath("//input[@value='CONTINUE TO PAYMENT']")).click();

		// Submit order
		driver.findElement(By.id("ec_cart_submit_order")).click();

		// Returning text for validation
		delay(2);
		String validation = driver.findElement(By.xpath("//h5[normalize-space()='Not a real order']")).getText();
		Assert.assertEquals(validation, "Not a real order");
	}
}