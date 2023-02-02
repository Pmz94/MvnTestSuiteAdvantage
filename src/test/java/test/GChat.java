package test;

// Chat
// Created by Marcelo Gonzalez

import base.Navegadores;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class GChat extends Navegadores {

	@Test
	public void chat() {
		delay(2);
		driver.findElement(By.id("chatLogo")).click();

		// Setting Window's IDs
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		print(allWindow + " dos");
		Iterator<String> iterator = allWindow.iterator();

		while(iterator.hasNext()) {
			String childWindow = iterator.next();
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				delay(3);
				driver.findElement(By.id("textMessage")).sendKeys("Hola");
				driver.findElement(By.id("btnSender")).click();
				delay(5);

				WebElement response = driver.findElement(By.xpath("//*[contains(text(),'Hola')]//following::p"));
				String textResponse = response.getText();
				boolean isCheck = false;
				if(textResponse.isEmpty() == false) {
					isCheck = true;
				}

				Assert.assertEquals(isCheck, true);
				driver.close();
				print("I'm out");
			}
		}

		driver.switchTo().window(mainWindow);
		print("Main window");
		driver.quit();
	}
}