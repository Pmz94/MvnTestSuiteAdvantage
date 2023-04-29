package pageobjects.academybugs.tsd;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tsd_Description extends BaseClass {

	public String description = "For neither the author of the land from hatred nor the hatred of adornment." +
		"However, it was not a matter of life for the author of football in the field." +
		"The class is suitable for the silent partners who turn to the shores through our marriages," +
		"through the Hymenaean projects. Mauris was just in." +
		"There is no such thing as a pot of football protein sauce." +
		"But neither did the developer.";

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