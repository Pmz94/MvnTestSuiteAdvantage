package tsd.academybugs;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-011
public class Tsd_AcademyBugs_PostReview extends BaseTest {

	final String comment = "Good product";
	final String author = "User";
	final String email = "correo@example.com";

	@Test
	public void postReview() {
		driver.findElement(By.xpath("//*[@id='ec_product_image_dnk-yellow-shoes']")).click();
		delay(2);

		driver.findElement(By.id("comment")).sendKeys(comment);
		driver.findElement(By.id("author")).sendKeys(author);
		driver.findElement(By.id("email")).sendKeys(email);

		// AcademyBugs on purpose bug at click
		driver.findElement(By.id("academy-comment-submit")).click();
		Assert.fail();
	}
}