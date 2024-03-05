package bsn.advantage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends NavbarPage {

    private final By productNameLabel = By.cssSelector("div#Description h1.roboto-regular.ng-binding");
    private final By saveToCartBtn = By.name("save_to_cart");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return this.getTextFromElement(productNameLabel);
    }

    public void verifyIsOnProductPage() {
        this.waitUntilPresent(productNameLabel);
        log.info("Product page is displayed: " + this.getProductName());
    }

    public void addToCart() {
        this.clickElement(saveToCartBtn);
        log.info("Product added to cart: " + this.getProductName());
    }
}
