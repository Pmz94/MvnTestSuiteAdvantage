
// Login
// Created by Samantha Jasso

package legacy.advantage.bsn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import legacy.BaseClass;

public class Bsn_Login extends BaseClass {
	WebDriver driver;
	
	// User variables
	public String user = "Juanito";
	public String password = "Prueba001";

	public Bsn_Login(WebDriver Drdriver) {
		driver = Drdriver;
	}

	public void Run() {
		// Input user locator
		WebElement btnUser = driver.findElement(By.xpath("//a[@id='hrefUserIcon']//*[name()='svg']"));
		btnUser.click();
		delay(2);

		WebElement inputUserName = driver.findElement(By.xpath("//input[@name=\"username\"]"));
		inputUserName.sendKeys(user);

		WebElement inputPassword = driver.findElement(By.xpath("//input[@name=\"password\"]"));
		inputPassword.sendKeys(password);

		WebElement buttonSign = driver.findElement(By.xpath("//button[@id=\"sign_in_btnundefined\"]"));
		buttonSign.click();
		delay(1);

		WebElement userMessage = driver.findElement(By.xpath("//*[@id=\"menuUserLink\"]/span"));
		delay(1);

		Assert.assertEquals(user, userMessage.getText());
		btnUser.click();
		delay(1);
		btnUser.click();
	}
}