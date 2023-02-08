package bsn;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.Browsers;
import base.Navegadores;


public class BSN_Chat extends Browsers{
	
	public String username = "Marcelo1";
	public String email = "marcelo@softtek.com";
	public String password = "S1234s";
	
	public BSN_Chat(WebDriver driver) {
		this.driver = driver;
	}
	
	@Test	
	
	public boolean chatOpen() throws InterruptedException {

		boolean isCheck = false;
		//login
		driver.findElement(By.id("menuUserSVGPath")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("sign_in_btnundefined")).click();
		
		//Chat
		Thread.sleep(2000);
		driver.findElement(By.id("chatLogo")).click();
		
		//Setting Window's IDs
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		System.out.println(allWindow + " dos");
		Iterator<String> iterator = allWindow.iterator();
		
		while(iterator.hasNext()) {
			String childWindow = iterator.next();
			
			if(!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(3000);
				driver.findElement(By.id("textMessage")).sendKeys("Hola");
				driver.findElement(By.id("btnSender")).click();
				Thread.sleep(5000);
				WebElement response = driver.findElement(By.xpath("//*[contains(text(),'Hola')]//following::p"));
				String textResponse = response.getText();
				
				if(textResponse.isEmpty() == false) {
					isCheck = true;
				} 
			
				driver.close();
				System.out.println("I'm out");
			}		
		}		
		driver.switchTo().window(mainWindow);
		System.out.println("Main window");
		driver.quit();
		return isCheck;
	}
}
