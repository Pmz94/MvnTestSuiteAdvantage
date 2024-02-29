
// CreateAccount
// Created by Maria Jose Rivera

package bsn.advantage;

import base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_Advantage_CreateAccount extends BasePage {

    private final By menuUser = By.id("menuUser");
    private final By createNewAccountLink = By.xpath("//*[@data-ng-click='createNewAccount()']");
    private final By usernameField = By.xpath("//*[@name='usernameRegisterPage']");
    private final By emailField = By.xpath("//*[@name='emailRegisterPage']");
    private final By passwordField = By.xpath("//*[@name='passwordRegisterPage']");
    private final By confirmPasswordField = By.xpath("//*[@name='confirm_passwordRegisterPage']");
    private final By iAgreeCheckBox = By.xpath("//*[@name='i_agree']");
    private final By registerButton = By.id("register_btn");
    private final By userAlreadyExistsMessage = By.xpath("//*[@id='registerPage']/article/sec-form/div[2]/label[1]");
    private final By menuUserLink = By.xpath("//*[@id='menuUserLink']/span");
    private final By logoutLink = By.xpath("//*[@id='loginMiniTitle']/label[3]");

    public Bsn_Advantage_CreateAccount(WebDriver driver) {
        super(driver);
    }

    public void createAccount(String user, String email, String password) {
        this.clickElement(menuUser);
        this.clickElement(createNewAccountLink);
        this.inputData(usernameField, user);
        this.inputData(emailField, email);
        this.inputData(passwordField, password);
        this.inputData(confirmPasswordField, password);
        this.checkBoxIfNotChecked(iAgreeCheckBox);
        this.clickElement(registerButton);

        List<WebElement> l = this.findElements(userAlreadyExistsMessage);
        if (l.isEmpty()) {
            Assert.assertEquals(this.getTextFromElement(menuUserLink), user);
            // cerrar sesion
            this.clickElement(menuUserLink);
            this.clickElement(logoutLink);
        } else {
            System.out.println("Esta cuenta ya existe");
        }
    }
}