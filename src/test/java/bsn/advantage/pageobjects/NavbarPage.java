package bsn.advantage.pageobjects;

import base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NavbarPage extends BasePage {

    // Home locator
    private final By advantageLogo = By.cssSelector("a[ng-click='go_up()']");
    private final By homeLink = By.linkText("HOME");

    // Search bar locators
    private final By searchIcon = By.id("searchSection");
    private final By searchInput = By.id("autoComplete");
    private final String specificProduct = "//a[normalize-space()='%s']";
    private final By foundProduct = By.xpath("//a[@class='product ng-scope']//img");
    private final By viewAllBtn = By.xpath("//a[@class='roboto-medium viewAll ng-scope']");
    private final By closeSearch = By.xpath("//div[@data-ng-click='closeSearchForce()']");

    // Login locators
    private final By menuUserIcon = By.id("menuUserLink");
    private final By menuUserLinkLabel = By.xpath("//*[@id='menuUserLink']/span");
    private final By loginMiniTitle = By.id("loginMiniTitle");
    private final By logoutLink = By.xpath("//*[@id='loginMiniTitle']/label[3]");

    // Login modal locators
    private final By loginModal = By.cssSelector("login-modal > div.PopUp > div");
    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By signUpBtn = By.xpath("//button[@id='sign_in_btn']");
    private final By createNewAccountLink = By.xpath("//a[@data-ng-click='createNewAccount()']");

    // Cart popup locators
    private final By cartIcon = By.id("shoppingCartLink");
    private final By popupCartProduct = By.cssSelector("tool-tip-cart#toolTipCart table tr#product");
    private final By popupCartProductName = By.tagName("h3");
    private final By popupCartProductRemove = By.cssSelector("div.closeDiv>div.removeProduct");
    private final By popupCheckoutBtn = By.id("checkOutPopUp");

    public NavbarPage(WebDriver driver) {
        super(driver);
    }

    public void goToHomePage() {
        this.clickElement(advantageLogo);
        log.info("Navigating to Home page");
    }

    public void searchByCategory(String category) {
        this.clickElement(searchIcon);
        this.inputData(searchInput, category);
        this.clickElement(viewAllBtn);
        this.clickElement(closeSearch);
        // Takes you to SearchResultsPage
    }

    public void searchBySpecificProduct(String specificProductName) {
        this.clickElement(searchIcon);
        this.inputData(searchInput, specificProductName);
        this.clickElement(foundProduct);
        // Takes you to ProductPage
    }

    public void goToCreateAccount() {
        this.clickElement(menuUserIcon);
        this.waitUntilVisible(loginModal);
        this.clickElement(createNewAccountLink);
    }

    public void login(String user, String password) {
        this.clickElement(menuUserIcon);
        this.waitUntilVisible(loginModal);
        this.inputData(usernameField, user);
        this.inputData(passwordField, password);
        this.clickElement(signUpBtn);
    }

    public boolean isUserLoggedIn(String expectedUser) {
        boolean isNameDisplayed = this.getTextFromElement(menuUserLinkLabel).trim().equals(expectedUser);
        log.info("Is name displayed on the navbar: " + isNameDisplayed);
        this.clickElement(menuUserIcon);
        boolean isLoginMiniTitleVisible = this.isPageObjectVisible(loginMiniTitle);
        log.info("Login mini title open: " + isLoginMiniTitleVisible);
        this.clickElement(menuUserIcon);
        this.waitUntilInvisible(loginMiniTitle);
        boolean isLoginMiniTitleInvisible = !this.isPageObjectVisible(loginMiniTitle);
        log.info("Login mini title closed: " + isLoginMiniTitleInvisible);
        return isNameDisplayed && isLoginMiniTitleVisible && isLoginMiniTitleInvisible;
    }

    public void logout() {
        this.clickElement(menuUserIcon);
        this.clickElement(logoutLink);
    }

    public void goToCart() {
        this.clickElement(cartIcon);
    }

    public boolean isCartPopupOpen() {
        return this.isPageObjectVisible(popupCheckoutBtn);
    }

    private boolean isProductInCartPopup(String expectedProductName) {
        // expected BOSE SOUNDLINK BLUETOOTH SPEAKER III
        List<WebElement> productListPopup = this.findElements(popupCartProduct);
        boolean productFound = false;
        for (WebElement product : productListPopup) {
            // actual BOSE SOUNDLINK BLUETOOTH SP...
            String actualProductName = product.findElement(popupCartProductName).getText().trim();
            if (actualProductName.equals(expectedProductName)) {
                productFound = true;
                product.findElement(popupCartProductRemove).click();
                break;
            }
        }
        return productFound;
    }
}
