
// AddingToCart
// Created by Samuel Arriola

package bsn.advantage;

import base.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bsn_Advantage_AddingToCart extends BasePage {

    private final By speakersImg = By.id("speakersImg");
    private final By speakersLink = By.id("speakersLink");
    private final By tabletsImg = By.id("tabletsImg");
    private final By tabletsLink = By.id("tabletsLink");
    private final By laptopsImg = By.id("laptopsImg");
    private final By laptopsLink = By.id("laptopsLink");
    private final By firstItemOnTheList = By.cssSelector("ul > .ng-scope:nth-child(1)");
    private final By productNameLabel = By.cssSelector("div#Description h1.roboto-regular.ng-binding");
    private final By saveToCartBtn = By.name("save_to_cart");
    private final By popupCartProduct = By.cssSelector("tool-tip-cart#toolTipCart table tr#product");
    private final By popupCartProductName = By.tagName("h3");
    private final By popupCartProductRemove = By.cssSelector("div.closeDiv>div.removeProduct");
    private final By popupCheckoutBtn = By.id("checkOutPopUp");
    private final By homeLink = By.linkText("HOME");

    public Bsn_Advantage_AddingToCart(WebDriver driver) {
        super(driver);
    }

    private By getCategoryImg(String category) {
        return switch (category.toLowerCase()) {
            case "speakers" -> speakersImg;
            case "tablets" -> tabletsImg;
            case "laptops" -> laptopsImg;
            default -> throw new InvalidArgumentException("Invalid category: " + category);
        };
    }

    private By getCategoryLink(String category) {
        return switch (category.toLowerCase()) {
            case "speakers" -> speakersLink;
            case "tablets" -> tabletsLink;
            case "laptops" -> laptopsLink;
            default -> throw new InvalidArgumentException("Invalid category: " + category);
        };
    }

    private void addToCart() {
        // Product page
        this.waitUntilPresent(productNameLabel);
        // click | name=save_to_cart |
        this.clickElement(saveToCartBtn);
        // Cart popup
        this.waitUntilVisible(popupCheckoutBtn);
        // click | linkText=HOME |
        this.clickElement(homeLink);
    }

    private void verifyIfProductIsInCartPopup(String expectedProductName) {
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
        Assert.assertTrue(productFound, expectedProductName + " not found in the cart popup");
    }

    public void addToCartBySpecificName(String category, String name) {
        // Home page
        this.hoverOver(this.getCategoryImg(category));
        // click | id=speakersLink |
        this.clickElement(this.getCategoryLink(category));
        // Category page
        // click | linkText=Bose Soundlink Bluetooth Speaker III |
        this.clickElement(By.linkText(name));
        // Product page
        this.addToCart();
    }

    public void addToCartByFirstItemOnTheList(String category) {
        // Home page
        // click | id=tabletsImg |
        this.clickElement(this.getCategoryImg(category));
        // Category page
        // click | css=ul > .ng-scope:nth-child(1) |
        this.clickElement(firstItemOnTheList);
        // Product page
        this.addToCart();
    }
}