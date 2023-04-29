package pageobjects.academybugs.tsd;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Tsd_TC016 extends BaseClass {

	@Test
	public void tc016() {
		try {
			WebElement btnProduct = driver.findElement(By.xpath("//*[@id=\"ec_add_to_cart_4\"]"));
			btnProduct.click();
		} catch(Exception e) {
			System.out.println("The botton is not working properly");
		}

		try {
			WebElement btnProductCheckout = driver.findElement(By.xpath("//*[@id=\"ec_added_to_cart_4\"]"));
			btnProductCheckout.click();
		} catch(Exception e) {
			System.out.println("The link is not working properly");
		}


		try {
			WebElement btnIncrease = driver.findElement(By.xpath("//td[@class='ec_plus_column']//child::input"));
			btnIncrease.click();
		} catch(Exception e) {
			System.out.println("The add botton is not working properly");
		}

		try {
			WebElement btnUpdate = driver.findElement(By.xpath("//div[@class='ec_cartitem_update_button']"));
			btnUpdate.click();
		} catch(Exception e) {
			System.out.println("The update botton is not working properly");
		}
	}
}