package bsn.academybugs;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Bsn_AcademyBugs_Breakdown extends BaseTest {
	WebDriver driver;

	public Bsn_AcademyBugs_Breakdown(WebDriver Drdriver) {
		driver = Drdriver;
	}

	public void Run() {
		delay(4);
		driver.findElement(By.xpath("//*[contains(text(),\"Accept cookies\")]")).click();
		String n1 = driver.findElement(By.xpath("//*[@class='test_title ec_product_title_type1']//following::span")).getText();
		driver.findElement(By.id("ec_add_to_cart_5")).click();
		driver.findElement(By.id("ec_product_image_effect_flamingo-tshirt")).click();
		String n2 = driver.findElement(By.xpath("//*[@class='ec_details_price ec_details_single_price']//following::span")).getText();
		driver.findElement(By.xpath("//input[@value='ADD TO CART']")).click();
		String grandTotal = driver.findElement(By.id("ec_cart_total")).getText();
		//Calculate difference between total price and price given by the webpage
		double price1 = PriceConvt(n1);
		double price2 = PriceConvt(n2);
		double finalPrice = price1 + price2;
		double totalPrice = PriceConvt(grandTotal);
		//Had to add $7.99 for shipping
		double compareNum = totalPrice - (finalPrice + 7.99);
		Assert.assertEquals(compareNum, 0.0);
	}

	// Conver String to double
	private double PriceConvt(String str) {
		String n1 = str.substring(1);
		double number = Double.parseDouble(n1);
		return number;
	}
}