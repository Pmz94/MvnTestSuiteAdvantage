
// CreateAccount
// Created by Maria Jose Rivera

package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browsers;

public class CreateAccount extends Browsers {
	// Create new account
	@Test
	public void createAccount() {
		delay(3);
		WebElement btnUser = driver.findElement(By.id("menuUser"));
		btnUser.click();
		delay(2);

		WebElement newAccountBtn = driver.findElement(By.xpath("//*[@data-ng-click='createNewAccount()']"));
		newAccountBtn.click();

		delay(3);

		WebElement userName = driver.findElement(By.xpath("//*[@name='usernameRegisterPage']"));
		userName.sendKeys(user);

		WebElement emailElement = driver.findElement(By.xpath("//*[@name='emailRegisterPage']"));
		emailElement.sendKeys(email);

		WebElement passwordElement = driver.findElement(By.xpath("//*[@name='passwordRegisterPage']"));
		passwordElement.sendKeys(password);

		WebElement passwordConfirmation = driver.findElement(By.xpath("//*[@name='confirm_passwordRegisterPage']"));
		passwordConfirmation.sendKeys(password);

		WebElement iAgree = driver.findElement(By.xpath("//*[@name='i_agree']"));
		iAgree.click();

		WebElement btnRegister = driver.findElement(By.id("register_btnundefined"));
		btnRegister.click();

		delay(3);

		List<WebElement> l = driver.findElements(By.xpath("//*[@id='registerPage']/article/sec-form/div[2]/label[1]"));
		if(l.size() == 0) {
			String username = driver.findElement(By.xpath("//*[@id='menuUserLink']/span")).getText();
			Assert.assertEquals(username, user);
			delay(2);
			// cerrar sesion para continuar con el caso de uso de login
			driver.findElement(By.id("menuUserLink")).click();
			driver.findElement(By.xpath("//*[@id='loginMiniTitle']/label[3]")).click();
		} else {
			print("Esta cuenta ya existe");
			Assert.assertTrue(true, "Esta cuenta ya existe");
			// regresar al index
			driver.findElement(By.className("logo")).click();
		}
	}
}
