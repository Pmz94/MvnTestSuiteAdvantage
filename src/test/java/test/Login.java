
// Login
// Created by Samantha Jasso

/*
package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browsers;

public class Login extends Browsers {
	@Test
	public void login() {
		delay(3);
		// Input user locator
		WebElement btnUser = driver.findElement(By.xpath("//a[@id='hrefUserIcon']//*[name()='svg']"));
		btnUser.click();
		delay(1);

		WebElement inputUserName = driver.findElement(By.xpath("//input[@name='username']"));
		inputUserName.sendKeys(user);

		WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
		inputPassword.sendKeys(password);

		WebElement buttonSign = driver.findElement(By.xpath("//button[@id='sign_in_btnundefined']"));
		buttonSign.click();

		delay(1);

		WebElement userMessage = driver.findElement(By.xpath("//*[@id='menuUserLink']/span"));
		print(userMessage.getText());

		delay(1);

		Assert.assertEquals(user, userMessage.getText());
		btnUser.click();
		delay(1);
		btnUser.click();
	}

	/*
	 * WebDriver driver; // User variables public String user = "Juanito"; public String password = "Prueba001";
	 * 
	 * public BLogin(WebDriver Drdriver) { driver = Drdriver; }
	 * 
	 * public void Run() { WebElement btnUser =
	 * driver.findElement(By.xpath("//a[@id='hrefUserIcon']//*[name()='svg']")); // Input user locator btnUser.click();
	 * delay(3); WebElement inputUserName = driver.findElement(By.xpath("//input[@name=\"username\"]"));
	 * inputUserName.sendKeys(user); WebElement inputPassword =
	 * driver.findElement(By.xpath("//input[@name=\"password\"]")); inputPassword.sendKeys(password); WebElement
	 * buttonSign = driver.findElement(By.xpath("//button[@id=\"sign_in_btnundefined\"]")); buttonSign.click();
	 * delay(1); WebElement userMessage = driver.findElement(By.xpath("//*[@id=\"menuUserLink\" ]/span"));
	 * System.out.println(userMessage.getText()); delay(1); Assert.assertEquals(user, userMessage.getText());
	 * btnUser.click(); delay(1); btnUser.click(); }
	 * /
}
*/