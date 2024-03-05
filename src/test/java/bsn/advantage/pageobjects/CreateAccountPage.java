package bsn.advantage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends NavbarPage {

    private final By pageTitle = By.xpath("//h3[text()='CREATE ACCOUNT']");
    private final By usernameField = By.xpath("//input[@name='usernameRegisterPage']");
    private final By emailField = By.xpath("//input[@name='emailRegisterPage']");
    private final By passwordField = By.xpath("//input[@name='passwordRegisterPage']");
    private final By confirmPasswordField = By.xpath("//input[@name='confirm_passwordRegisterPage']");
    private final By allowOffersCheckBox = By.xpath("//input[@name='allowOffersPromotion']");
    private final By iAgreeCheckBox = By.xpath("//input[@name='i_agree']");
    private final By registerButton = By.id("register_btn");
    private final By userAlreadyExistsMessage = By.cssSelector("label.center.block.smollMargin[data-ng-show='!registerSuccess']");

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public void verifyIsOnCreateAccountPage() {
        this.waitUntilVisible(pageTitle);
        log.info("On Create Account Page");
    }

    public void fillRequiredFields(String user, String email, String password) {
        this.inputData(usernameField, user);
        this.inputData(emailField, email);
        this.inputData(passwordField, password);
        this.inputData(confirmPasswordField, password);
        this.clickCheckBox(allowOffersCheckBox, false);
        this.clickCheckBox(iAgreeCheckBox, true);
    }

    public void clickRegisterButton() {
        this.clickElement(registerButton);
    }

    public boolean isUserAlreadyExistsMessageVisible() {
        return this.isPageObjectVisible(userAlreadyExistsMessage);
    }
}
