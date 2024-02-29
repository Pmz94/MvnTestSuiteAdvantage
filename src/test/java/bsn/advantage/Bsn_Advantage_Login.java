
// Login
// Created by Samantha Jasso

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Bsn_Advantage_Login extends BasePage {

    private final By userIcon = By.xpath("//a[@id='hrefUserIcon']//*[name()='svg']");
    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By signUpBtn = By.xpath("//button[@id='sign_in_btn']");
    private final By menuUserLink = By.xpath("//*[@id='menuUserLink']/span");

    public Bsn_Advantage_Login(WebDriver driver) {
        super(driver);
    }

    public void login(String user, String password) {
        // Input user locator
        this.clickElement(userIcon);
        this.inputData(usernameField, user);
        this.inputData(passwordField, password);
        this.clickElement(signUpBtn);
    }

    public void verifyUserLoggedIn(String expectedUser) {
        Assert.assertEquals(this.getTextFromElement(menuUserLink), expectedUser);
        this.clickElement(userIcon);
        this.clickElement(userIcon);
    }
}