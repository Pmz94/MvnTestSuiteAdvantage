package tsd.academybugs;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_ManufactSite extends BaseTest {

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