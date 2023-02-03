
// Checkout
// Created by Pedro Muñoz

package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Browsers;

public class Checkout extends Browsers {
	@Test
	public void checkout() {
		driver.findElement(By.id("shoppingCartLink")).click();
		delay(3);
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/login-modal/div/div/div[3]/a[2]")));

		// Ver si hay productos en el carrito
		List<WebElement> l = driver.findElements(By.xpath("//*[@id='shoppingCart']/div/label"));

		if(l.size() == 0) {
			driver.findElement(By.id("checkOutButton")).click();
			delay(3);
			driver.findElement(By.id("next_btn")).click();

			// Check payment method
			driver.findElement(By.xpath("//*[@id='paymentMethod']/div/div[1]/div[1]/input")).click();

			// payment account credentials
			driver.findElement(By.name("safepay_username")).sendKeys(user);
			driver.findElement(By.name("safepay_password")).sendKeys(password);

			// checkbox
			WebElement checkbox1 = driver.findElement(By.name("save_safepay"));
			if(checkbox1.isSelected()) checkbox1.click();

			// Total
			String total1 = driver.findElement(By.id("//*[@id='userCart']/div[2]/label[2]/span")).getText();

			WebElement boton = driver.findElement(By.id("pay_now_btn_SAFEPAY"));
			if(boton.isEnabled()) boton.click();

			WebElement mensajec = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/h2/span"));
			String mensaje = mensajec.getText();
			print(mensaje);

			int pass = 0;

			if(mensaje.equals("Thank you for buying with Advantage")) {
				print("mensaje validado");
				pass++;
			}

			String rastreo = driver.findElement(By.id("trackingNumberLabel")).getText();
			if(!rastreo.isEmpty()) {
				print("Num rastreo obtenido");
				pass++;
			}

			String orden = driver.findElement(By.id("orderNumberLabel")).getText();
			if(!orden.isEmpty()) {
				print("Num orden obtenido");
				pass++;
			}

			String total2 = driver.findElement(By.xpath("//*[@id='orderPaymentSuccess']/div/div[3]/div[3]/label/a"))
				.getText();
			if(total1.equals(total2)) {
				print("Pagaste justo lo que costo");
			}

			Assert.assertEquals(pass, 3);
		} else {
			String texto = l.get(0).getText().trim();
			print("No hay productos en el carrito");
			Assert.assertEquals(texto, "Your shopping cart is empty");
		}
	}
}
