package tsd;

import base.Browsers;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_Description extends Browsers {
	public String description = "Nam nec tellus a odio tincidunt auctor a ornare odio. "
		+ "Sed non mauris vitae erat consequat auctor eu in elit. "
		+ "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. "
		+ "Mauris in erat justo. Nullam ac urna eu felis dapibus condimentum sit amet a augue. "
		+ "Sed non neque elit sed.";

	@Test
	public void signIn() {
		findElement("xpath", "//*[contains(text(),\"Accept cookies\")]").click();

		WebElement product = findElement("id", "ec_product_li_flamingo-tshirt");
		product.click();
		delay(2);

		WebElement lblDescription = findElement("xpath", "//*[@id='post-5547']/div/section/div[1]/div[3]/form/div[4]");
		String desc = lblDescription.getText();
		Assert.assertEquals(desc, description);
	}
}