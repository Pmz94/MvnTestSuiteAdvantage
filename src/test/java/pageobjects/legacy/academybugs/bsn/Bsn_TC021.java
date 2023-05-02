package pageobjects.legacy.academybugs.bsn;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Bsn_TC021 extends BaseClass {
	WebDriver driver;

	// User variables
	public String firstName = "John";
	public String lastName = "Cena";
	public String email = "john.cena1@wwe.com";
	public String pass = "Prueba001";

	public Bsn_TC021(WebDriver Drdriver) {
		driver = Drdriver;
	}

	public void Run() {
		WebElement element = driver.findElement(By.id("ec_product_image_dnk-yellow-shoes"));
		element.click();

		WebElement signUpBtn = driver.findElement(By.id("ec_account_login_email_widget"));
		signUpBtn.sendKeys(email);
		delay(1);

		WebElement password = driver.findElement(By.id("ec_account_login_password_widget"));
		password.sendKeys(pass);
		delay(1);

		WebElement cookies = driver.findElement(By.xpath("//*[@id='cc-window']/div/a[2]"));
		cookies.click();
		delay(2);

		WebElement signInBtn = driver.findElement(By.xpath("//*[@id='login-from-side-menu']/div[6]/button"));
		signInBtn.click();
		delay(2);

		WebElement closeBug = driver.findElement(By.xpath("//*[@id='popmake-4406']/button"));
		closeBug.click();
		delay(3);

		WebElement billingButton = driver.findElement(By.xpath("//*[@id='ec_account_dashboard']/div[3]/div[2]/a"));
		billingButton.click();
		delay(4);

		WebElement drpCountry = driver.findElement(By.id("ec_account_billing_information_country"));
		Select dropdown = new Select(drpCountry);
		dropdown.selectByVisibleText("Mexico");
		delay(1);

		WebElement fName = driver.findElement(By.id("ec_account_billing_information_first_name"));
		fName.sendKeys(firstName);
		delay(1);

		WebElement lName = driver.findElement(By.id("ec_account_billing_information_last_name"));
		lName.sendKeys(lastName);
		delay(1);

		WebElement companyName = driver.findElement(By.id("ec_account_billing_information_company_name"));
		companyName.sendKeys("Softtek");
		delay(1);

		WebElement address = driver.findElement(By.id("ec_account_billing_information_address"));
		address.sendKeys("Calle 123");
		delay(1);

		WebElement city = driver.findElement(By.id("ec_account_billing_information_city"));
		city.sendKeys("Ensenada");
		delay(1);

		WebElement state = driver.findElement(By.id("ec_account_billing_information_state"));
		state.sendKeys("Baja California");
		delay(1);

		WebElement zipCode = driver.findElement(By.id("ec_account_billing_information_zip"));
		zipCode.sendKeys("123456");
		delay(1);

		WebElement phone = driver.findElement(By.id("ec_account_billing_information_phone"));
		phone.sendKeys("6461234567");
		delay(1);

		WebElement updateBtn = driver.findElement(By.xpath("//*[@id='ec_account_billing_information']/div[1]/form/div[12]/input"));
		updateBtn.click();
		delay(1);

		WebElement loadingBtn = driver.findElement(By.xpath("//*[@id='ec_account_billing_information']/div[1]/form/div[12]/span"));
		loadingBtn.click();
		delay(3);

		WebElement closeIframe = driver.findElement(By.xpath("//*[@id='popmake-4406']/button"));
		closeIframe.click();
		delay(1);

		WebElement closeIframe2 = driver.findElement(By.xpath("//*[@id='popmake-4393']/button"));
		closeIframe2.click();
		delay(2);

		String actualEmail = driver.findElement(By.xpath("//*[@id='ec_account_dashboard']/div[2]/div[3]")).getText();
		print(actualEmail);

		Assert.assertEquals(actualEmail, email);
	}
}