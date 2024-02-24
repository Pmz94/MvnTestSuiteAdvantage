package legacy.academybugs.tsd;

import legacy.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-011
public class Tsd_PostReview extends BaseClass {

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