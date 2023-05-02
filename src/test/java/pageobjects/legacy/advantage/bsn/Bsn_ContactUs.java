
// ContactUs
// Created by Mayra Juarez

package pageobjects.legacy.advantage.bsn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseClass;
import org.testng.Assert;
import java.time.Duration;

public class Bsn_ContactUs extends BaseClass {
	WebDriver driver;
	public String email = "test001@email.com";
	public String category = "Mice";
	public String product = "HP Z4000 Wireless Mouse";
	public String subject = "Producto defectuoso";

	public Bsn_ContactUs(WebDriver driver) {
		this.driver = driver;
	}

	public void Run() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		delay(2);

		WebElement btnContactUs = driver.findElement(By.xpath("//*[text()='CONTACT US' and @class='menu navLinks roboto-regular ng-scope']"));
		btnContactUs.click();
		delay(1);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("categoryListboxContactUs")));
		Select selectorCategory = new Select(driver.findElement(By.name("categoryListboxContactUs")));
		Select selectorProduct = new Select(driver.findElement(By.name("productListboxContactUs")));
		WebElement txtEmail = driver.findElement(By.name("emailContactUs"));
		WebElement txtSubject = driver.findElement(By.name("subjectTextareaContactUs"));
		WebElement btnSend = driver.findElement(By.id("send_btnundefined"));
		delay(1);

		wait.until(ExpectedConditions.elementToBeClickable(By.name("categoryListboxContactUs")));
		selectorCategory.selectByVisibleText(category);
		selectorProduct.selectByVisibleText(product);
		txtEmail.sendKeys(email);
		txtSubject.sendKeys(subject);
		btnSend.click();

		delay(1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='registerSuccessCover']/div/p")));
		WebElement successMessage = driver.findElement(By.xpath("//*[@id='registerSuccessCover']/div/p"));
		String actualMessage = successMessage.getText();

		String expectedMessage = "Thank you for contacting Advantage support.";
		Assert.assertEquals(actualMessage, expectedMessage);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' CONTINUE SHOPPING ']")));
		WebElement btnContinueShopping = driver.findElement(By.xpath("//*[text()=' CONTINUE SHOPPING ']"));
		btnContinueShopping.click();
	}
}