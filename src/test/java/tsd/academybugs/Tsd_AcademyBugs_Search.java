package tsd.academybugs;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

// TC-012
public class Tsd_AcademyBugs_Search extends BaseTest {

	final String searchKeyword = "jeans";
	final String xpath_label_nofound = "//*[@id='ec_product_page']/div[@class='ec_products_no_results']";

	@Test
	public void search() {
		driver.findElement(By.xpath("//*[@id='ec_product_image_dnk-yellow-shoes']")).click();
		delay(2);

		driver.findElement(By.xpath("//*[@id='ec_searchwidget-3']/div/form/input[1]")).sendKeys(searchKeyword);
		driver.findElement(By.xpath("//*[@id='ec_searchwidget-3']/div/form/input[2]")).click();
		delay(2);

		if(driver.findElements(By.xpath(xpath_label_nofound)).isEmpty()) {
			List<WebElement> resFound = driver.findElements(By.xpath("//*[@id='ec_store_product_list']"));
			Actions actions = new Actions(driver);
			actions.moveToElement(resFound.get(0)).perform();
			Assert.assertTrue(resFound.size() <= 1);
		} else {
			String noResText = driver.findElement(By.xpath(xpath_label_nofound)).getText();
			Assert.assertEquals(noResText, "No Results Found");
		}
	}
}