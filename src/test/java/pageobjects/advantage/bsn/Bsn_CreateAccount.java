
// CreateAccount
// Created by Maria Jose Rivera

package pageobjects.advantage.bsn;

import java.util.List;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_CreateAccount extends BaseClass {

	WebDriver driver;
	public String user = "Teams3";
	public String email = "teams3@mail.com";
	public String password = "Teams03";

	public Bsn_CreateAccount(WebDriver driver) {
		this.driver = driver;
	}

	public void Run() {
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement btnUser = driver.findElement(By.id("menuUser"));
		btnUser.click();

		delay(2);

		WebElement createNewAccountBtn = driver.findElement(By.xpath("//*[@data-ng-click='createNewAccount()']"));
		createNewAccountBtn.click();

		WebElement usernameBox = driver.findElement(By.xpath("//*[@name='usernameRegisterPage']"));
		usernameBox.sendKeys(user);

		WebElement emailBox = driver.findElement(By.xpath("//*[@name='emailRegisterPage']"));
		emailBox.sendKeys(email);

		WebElement passwordBox = driver.findElement(By.xpath("//*[@name='passwordRegisterPage']"));
		passwordBox.sendKeys(password);

		WebElement passwordConfirmation = driver.findElement(By.xpath("//*[@name='confirm_passwordRegisterPage']"));
		passwordConfirmation.sendKeys(password);

		WebElement iAgreeCheckBox = driver.findElement(By.xpath("//*[@name='i_agree']"));
		iAgreeCheckBox.click();

		WebElement btnRegister = driver.findElement(By.id("register_btnundefined"));
		btnRegister.click();

		List<WebElement> l = driver.findElements(By.xpath("//*[@id='registerPage']/article/sec-form/div[2]/label[1]"));
		if(l.size() == 0) {
			String username = driver.findElement(By.xpath("//*[@id='menuUserLink']/span")).getText();
			Assert.assertEquals(username, user);
			delay(2);
			// cerrar sesion
			driver.findElement(By.id("menuUserLink")).click();
			driver.findElement(By.xpath("//*[@id='loginMiniTitle']/label[3]")).click();
		} else {
			Assert.assertTrue(true, "Esta cuenta ya existe");
		}
	}
}