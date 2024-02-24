package bsn.academybugs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import base.Browsers;

public class Bsn_AcademyBugs_TC004 extends Browsers {
	WebDriver driver;

	// User variables
	public String firstName = "John";
	public String lastName = "Cena";
	public String email = "john.cena1@wwe.com";
	public String pass = "Prueba001";

	public Bsn_AcademyBugs_TC004(WebDriver Drdriver) {
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

		WebElement registeredEmail = driver.findElement(By.xpath("//*[@id='ec_account_dashboard']/div[2]/div[3]"));
		delay(1);

		Assert.assertEquals(email, registeredEmail.getText());
	}
}