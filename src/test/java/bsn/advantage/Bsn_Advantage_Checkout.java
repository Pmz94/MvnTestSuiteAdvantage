
// Checkout
// Created by Pedro Muñoz

package bsn.advantage;

import base.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_Advantage_Checkout extends Browsers {
	WebDriver driver;
	public boolean emptyCar = true;
	public String emptyCarMessage = "";
	public String safepay_user = "teams3";
	public String safepay_password = "Teams03";

	public Bsn_Advantage_Checkout(WebDriver driver) {
		this.driver = driver;
	}

	public void Run() {
		driver.findElement(By.id("shoppingCartLink")).click();
		delay(3);

		// Ver si hay productos en el carrito
		WebElement l = driver.findElement(By.xpath("//*[@id='shoppingCart']/div/label"));

		if(!l.isDisplayed()) {
			driver.findElement(By.id("checkOutButton")).click();
			delay(3);
			driver.findElement(By.id("next_btn")).click();

			// Check payment method
			driver.findElement(By.xpath("//*[@id='paymentMethod']/div/div[1]/div[1]/input")).click();

			// payment account credentials
			driver.findElement(By.name("safepay_username")).sendKeys(safepay_user);
			driver.findElement(By.name("safepay_password")).sendKeys(safepay_password);

			// checkbox
			WebElement checkbox1 = driver.findElement(By.name("save_safepay"));
			if(checkbox1.isSelected()) checkbox1.click();

			// Total
			String total1 = driver.findElement(By.xpath("//*[@id='userCart']/div[2]/label[2]/span")).getText();

			delay(2);

			// WebElement payButton = driver.findElement(By.id("pay_now_btn_SAFEPAY"));
			// print("" + payButton.isEnabled());
			// if(payButton.isEnabled()) payButton.click();

			driver.findElement(By.xpath("//*[@id='pay_now_btn_SAFEPAY']")).click();

			delay(4);

			WebElement mensajec = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/h2/span"));
			String mensaje = mensajec.getText();
			print(mensaje);

			int pass = 0;

			if(mensaje.equals("Thank you for buying with Advantage")) {
				print("mensaje validado");
				pass++;
			}

			String rastreo = driver.findElement(By.id("trackingNumberLabel")).getText();
			if(!rastreo.equals("")) {
				print("Num rastreo obtenido");
				pass++;
			}

			String orden = driver.findElement(By.id("orderNumberLabel")).getText();
			if(!orden.equals("")) {
				print("Num orden obtenido");
				pass++;
			}

			String total2 = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/div/div[3]/div[3]/label/a")).getText();
			if(total1.equals(total2)) {
				print("Pagaste justo lo que costo");
			}

			Assert.assertEquals(pass, 3);
		} else {
			String texto = l.getText().trim();
			print("No hay productos en el carrito");
			Assert.assertEquals(texto, "Your shopping cart is empty");
		}
	}
}