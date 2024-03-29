
// Chat
// Created by Marcelo Gonzalez

package bsn.advantage;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BaseTest;

public class Bsn_Advantage_Chat extends BaseTest {

	public String username = "Marcelo1";
	public String password = "S1234s";

	public Bsn_Advantage_Chat(WebDriver driver) {
		this.driver = driver;
	}

	public boolean chatOpen() {
		boolean isCheck = false;

		//login
		driver.findElement(By.id("menuUserSVGPath")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("sign_in_btnundefined")).click();

		//Chat
		delay(2);
		driver.findElement(By.id("chatLogo")).click();

		//Setting Window's IDs
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		// System.out.println(allWindow + " dos");
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

				if(!textResponse.isEmpty()) {
					isCheck = true;
				}
			}
		}

		driver.switchTo().window(mainWindow);
		return isCheck;
	}
}