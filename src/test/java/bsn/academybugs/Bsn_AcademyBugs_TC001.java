package bsn.academybugs;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Bsn_AcademyBugs_TC001 extends BaseTest {
	WebDriver driver;

	// User variables
	public String firstName = "John";
	public String lastName = "Cena";
	public String email = "john.cena1@wwe.com";
	public String pass = "Prueba001";

	public Bsn_AcademyBugs_TC001(WebDriver Drdriver) {
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

		WebElement mainPage = driver.findElement(By.id("menu-item-561"));
		mainPage.click();
		delay(2);
	}
}