package bsn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Bsn_CreateAccount {
	
	WebDriver driver;
	public String user = "Teams3";
	public String email = "teams3@mail.com";
	public String password = "Teams03";
	
	public Bsn_CreateAccount(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public void Run() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement btnUser = driver.findElement(By.id("menuUser"));
		btnUser.click();
		
		WebElement createNewAccountBtn = driver.findElement(By.xpath("//*[@data-ng-click='createNewAccount()']"));
		createNewAccountBtn.click();
		
		WebElement usernameBox = driver.findElement(By.xpath("//*[@name='usernameRegisterPage']"));
		usernameBox.sendKeys(user);	
		WebElement emailBox = driver.findElement(By.xpath("//*[@name='emailRegisterPage']"));
		emailBox.sendKeys(email);	
		WebElement passwordBox = driver.findElement(By.xpath("//*[@name='passwordRegisterPage']"));
		passwordBox.sendKeys(password);	
		WebElement passwordConfirmation = driver.findElement(By.xpath("//*[@name='confirm_passwordRegisterPage']"));
		passwordConfirmation.sendKeys("Team03");	
		WebElement iAgreeCheckBox = driver.findElement(By.xpath("//*[@name='i_agree']"));
		iAgreeCheckBox.click();	
		WebElement btnRegister = driver.findElement(By.id("register_btnundefined"));
		btnRegister.click();
		
	}

}
