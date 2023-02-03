
// Contact
// Created by Mayra Juarez

package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.Browsers;

public class Contact extends Browsers {
	WebDriver driver;
	public String email = "test001@email.com";
	public String category = "Mice";
	public String product = "HP Z4000 Wireless Mouse";
	public String subject = "Producto defectuoso";

	@Test
	public void contact() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		delay(2);

		WebElement btnContactUs = driver
			.findElement(By.xpath("//*[text()='CONTACT US' and @class='menu navLinks roboto-regular ng-scope']"));
		btnContactUs.click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("categoryListboxContactUs")));
		delay(3);
		Select selectorCategory = new Select(driver.findElement(By.name("categoryListboxContactUs")));
		Select selectorProduct = new Select(driver.findElement(By.name("productListboxContactUs")));
		WebElement txtEmail = driver.findElement(By.name("emailContactUs"));
		WebElement txtSubject = driver.findElement(By.name("subjectTextareaContactUs"));
		WebElement btnSend = driver.findElement(By.id("send_btnundefined"));
		selectorCategory.selectByVisibleText(category);
		selectorProduct.selectByVisibleText(product);
		txtEmail.sendKeys(email);
		txtSubject.sendKeys(subject);
		btnSend.click();
		delay(1);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' CONTINUE SHOPPING ']")));
		delay(3);
		WebElement btnContinueShopping = driver.findElement(By.xpath("//*[text()=' CONTINUE SHOPPING ']"));
		btnContinueShopping.click();
		driver.quit();
	}

	/*
	 * public FContact(WebDriver driver) { this.driver = driver; }
	 * 
	 * public void Run() { try { WebDriverWait wait = new WebDriverWait(driver, 30); delay(2);
	 * 
	 * WebElement btnContactUs =
	 * driver.findElement(By.xpath("//*[text()='CONTACT US' and @class='menu navLinks roboto-regular ng-scope']"));
	 * btnContactUs.click();
	 * 
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("categoryListboxContactUs"))); //delay(2);
	 * Select selectorCategory = new Select(driver.findElement(By.name("categoryListboxContactUs"))); Select
	 * selectorProduct = new Select(driver.findElement(By.name("productListboxContactUs"))); WebElement txtEmail =
	 * driver.findElement(By.name("emailContactUs")); WebElement txtSubject =
	 * driver.findElement(By.name("subjectTextareaContactUs")); WebElement btnSend =
	 * driver.findElement(By.id("send_btnundefined")); selectorCategory.selectByVisibleText(category);
	 * selectorProduct.selectByVisibleText(product); txtEmail.sendKeys(email); txtSubject.sendKeys(subject);
	 * btnSend.click(); delay(1);
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' CONTINUE SHOPPING ']")));
	 * WebElement btnContinueShopping = driver.findElement(By.xpath("//*[text()=' CONTINUE SHOPPING ']"));
	 * btnContinueShopping.click(); driver.quit(); } catch(Exception ex) { System.out.println(ex.getMessage()); } }
	 */
}
