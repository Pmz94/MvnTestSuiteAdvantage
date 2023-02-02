package test;

// AddToCart
// Created by Samuel Arreola

import base.Navegadores;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DAddToCart extends Navegadores {
	@Test(description = "add 1 items from 3 different categories to cart")
	public void addToCart() {
		delay(5);
		// 3 | click | id=speakersLink |
		driver.findElement(By.id("speakersLink")).click();
		delay(5);
		// 4 | click | linkText=Bose Soundlink Bluetooth Speaker III |
		driver.findElement(By.linkText("Bose Soundlink Bluetooth Speaker III")).click();
		delay(5);
		// 5 | click | name=save_to_cart |
		driver.findElement(By.name("save_to_cart")).click();
		// 6 | click | linkText=HOME |
		driver.findElement(By.linkText("HOME")).click();
		delay(5);
		// 7 | click | id=tabletsImg |
		driver.findElement(By.id("tabletsImg")).click();
		delay(5);
		// 8 | click | css=ul > .ng-scope:nth-child(1) |
		driver.findElement(By.cssSelector("ul > .ng-scope:nth-child(1)")).click();
		delay(5);
		// 9 | click | name=save_to_cart |
		driver.findElement(By.name("save_to_cart")).click();
		// 10 | click | linkText=HOME |
		driver.findElement(By.linkText("HOME")).click();
		delay(5);
		// 11 | click | id=laptopsImg |
		driver.findElement(By.id("laptopsImg")).click();
		delay(5);
		// 12 | click | linkText=HP Chromebook 14 G1(ENERGY STAR) |
		driver.findElement(By.linkText("HP Chromebook 14 G1(ENERGY STAR)")).click();
		delay(5);
		// 13 | click | name=save_to_cart |
		driver.findElement(By.name("save_to_cart")).click();

		/*
		// 14 | click | id=checkOutPopUp |
		driver.findElement(By.id("checkOutPopUp")).click();
		delay(5);
		// 15 click next
		driver.findElement(By.id("next_btn")).click();
		delay(5);
		// 16 adding username and password for pay
		driver.findElement(By.name("safepay_username")).sendKeys("SamuelTest");
		driver.findElement(By.name("safepay_password")).sendKeys("Softtek01");
		driver.findElement(By.id("pay_now_btn_SAFEPAY")).click();
		delay(5);
		//Validate
		String textValidate = driver.findElement(By.className("roboto-regular ng-scope")).getText();
		Assert.assertEquals(textValidate, "Thank you for buying with Advantage");
		*/
	}
}
