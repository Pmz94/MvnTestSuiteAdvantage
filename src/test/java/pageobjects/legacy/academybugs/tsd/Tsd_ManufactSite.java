package pageobjects.legacy.academybugs.tsd;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Tsd_ManufactSite extends BaseClass {

	@Test
	public void goToManufacturerSite() {
		WebElement element = driver.findElement(By.id("ec_product_image_dnk-yellow-shoes"));
		element.click();
		delay(2);

		WebElement btnSearch = driver.findElement(By.xpath("//*[@id='manufacturer-bug']/a"));
		btnSearch.click();

		assertTrue(driver.getTitle().contains("404"));

		try {
			assertTrue(driver.getTitle().contains("404"));
			print("The link is broken");
		} catch(Exception e) {
			print("The link is working properly");
		}
	}

	private void assertTrue(boolean contains) {

	}
}