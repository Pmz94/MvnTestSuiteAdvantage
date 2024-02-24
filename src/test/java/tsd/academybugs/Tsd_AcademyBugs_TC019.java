package tsd.academybugs;

import base.Browsers;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_TC019 extends Browsers {
	@Test
	public void tc019() {
		try {
			WebElement btnProduct = driver.findElement(By.xpath("//*[@id=\"ec_product_image_effect_3061856\"]/a"));
			btnProduct.click();
		} catch(Exception e) {
			System.out.println("The product botton is not working properly");
		}

		String existRelated = driver.findElement(By.xpath("//*[@id='post-6192']/div/section/div[3]/h3")).getText();

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='post-6192']/div/section/div[3]/h3"))));
			System.out.println("The searched text: " + existRelated + " exist");
		} catch(Exception e) {
			System.out.println("The searched text: " + existRelated + " does NOT exist");
		}
	}
}