package tsd;

import base.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_UpdateShippingInfo extends Browsers {

	final String email = "correo@example.com";
	final String passwd = "Admin1";
	final String shipp_country = "US";
	final String shipp_firstname = "Pedro";
	final String shipp_lastname = "Torres";
	final String shipp_address = "1234 E Broadway";
	final String shipp_city = "Chicago";
	final String shipp_state = "IL";
	final String shipp_zip = "83702";
	final String shipp_phone = "5204845533";
	final String expectedMessage = "Your shipping information was updated successfully.";

	@Test
	public void updateShippingInfo() {
		findElement("xpath", "//*[@id='ec_product_image_dnk-yellow-shoes']").click();
		delay(2);

		findElement("xpath", "//*[@id='login-from-side-menu']/div[4]/p/a").click();

		WebElement txb_email = findElement("id", "ec_account_login_email");
		txb_email.clear();
		txb_email.sendKeys(email);

		WebElement txb_pass = findElement("id", "ec_account_login_password");
		txb_pass.clear();
		txb_pass.sendKeys(passwd);

		findElement("xpath", "//*[@id='display-account-login-form-start']/div[5]/input").click();

		findElement("xpath", "//*[@id='ec_account_dashboard']/div[3]/div[3]/a").click();

		delay(3);

		// Limpiar y llenar formulario
		Select drpCountry = new Select(findElement("id", "ec_account_shipping_information_country"));
		drpCountry.selectByValue(shipp_country);
		delay(1);

		WebElement firstName = findElement("id", "ec_account_shipping_information_first_name");
		firstName.clear();
		firstName.sendKeys(shipp_firstname);

		WebElement lastName = findElement("id", "ec_account_shipping_information_last_name");
		lastName.clear();
		lastName.sendKeys(shipp_lastname);

		WebElement address = findElement("id", "ec_account_shipping_information_address");
		address.clear();
		address.sendKeys(shipp_address);

		WebElement city = findElement("id", "ec_account_shipping_information_city");
		city.clear();
		city.sendKeys(shipp_city);

		Select drpState = new Select(findElement("id", "ec_account_shipping_information_state_US"));
		drpState.selectByValue(shipp_state);

		WebElement zip = findElement("id", "ec_account_shipping_information_zip");
		zip.clear();
		zip.sendKeys(shipp_zip);

		WebElement phone = findElement("id", "ec_account_shipping_information_phone");
		phone.clear();
		phone.sendKeys(shipp_phone);

		delay(1);

		findElement("id", "shipping_info_form_ec").click();

		delay(2);

		String message = findElement("xpath", "//*[@class='ec_account_success']/div").getText();

		Assert.assertEquals(message, expectedMessage);
	}
}