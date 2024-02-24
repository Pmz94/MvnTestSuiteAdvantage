
// AddingToCart
// Created by Samuel Arriola

package legacy.advantage.bsn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import legacy.BaseClass;

public class Bsn_AddingToCart extends BaseClass {
	WebDriver driver;

	public Bsn_AddingToCart(WebDriver driver) {
		this.driver = driver;
	}

	public void Run() {
		try {
			delay(3);
			// 3 | click | id=speakersLink |
			driver.findElement(By.id("speakersLink")).click();
			delay(2);
			// 4 | click | linkText=Bose Soundlink Bluetooth Speaker III |
			driver.findElement(By.linkText("Bose Soundlink Bluetooth Speaker III")).click();
			delay(1);
			// 5 | click | name=save_to_cart |
			driver.findElement(By.name("save_to_cart")).click();
			// 6 | click | linkText=HOME |
			driver.findElement(By.linkText("HOME")).click();

			delay(3);
			// 7 | click | id=tabletsImg |
			driver.findElement(By.id("tabletsImg")).click();
			delay(2);
			// 8 | click | css=ul > .ng-scope:nth-child(1) |
			driver.findElement(By.cssSelector("ul > .ng-scope:nth-child(1)")).click();
			delay(1);
			// 9 | click | name=save_to_cart |
			driver.findElement(By.name("save_to_cart")).click();
			// 10 | click | linkText=HOME |
			driver.findElement(By.linkText("HOME")).click();

			delay(3);
			// 11 | click | id=laptopsImg |
			driver.findElement(By.id("laptopsImg")).click();
			delay(2);
			// 12 | click | linkText=HP Chromebook 14 G1(ENERGY STAR) |
			driver.findElement(By.linkText("HP Chromebook 14 G1(ENERGY STAR)")).click();
			delay(1);
			// 13 | click | name=save_to_cart |
			driver.findElement(By.name("save_to_cart")).click();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}