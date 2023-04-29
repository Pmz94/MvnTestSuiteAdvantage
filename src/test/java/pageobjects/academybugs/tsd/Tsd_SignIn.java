package pageobjects.academybugs.tsd;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;

public class Tsd_SignIn extends BaseClass {

	public String email = "testAcademy@email.com";
	public String password = "Password";

	@Test
	public void signIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		delay(2);

		driver.findElement(By.xpath("//*[contains(text(),\"Accept cookies\")]")).click();
		WebElement product = driver.findElement(By.id("ec_product_image_dnk-yellow-shoes"));
		product.click();
		delay(2);

		WebElement txtEmail = driver.findElement(By.id("ec_account_login_email_widget"));
		WebElement txtPassword = driver.findElement(By.id("ec_account_login_password_widget"));
		WebElement btnLogIn = driver.findElement(By.xpath("//*[@id='login-from-side-menu']/div[6]/button"));
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(password);
		btnLogIn.click();

		// Cerrar ventana de errores
		delay(1);
		driver.findElement(By.xpath("//*[@id='popmake-4406']/button")).click();
		//delay(2);
		//driver.findElement(By.xpath("//*[@id='popmake-4393']/button")).click();
		delay(2);

		// Comparar el email que se muestra como info de usuario y el email de entrada
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ec_account_dashboard']/div[2]/div[3]")));
		WebElement labelEmail = driver.findElement(By.xpath("//*[@id='ec_account_dashboard']/div[2]/div[3]"));
		String _email = labelEmail.getText();
		Assert.assertEquals(_email, email);
	}
}