package bsn.advantage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

public class HomePage extends NavbarPage {

    private final By ourProducts = By.id("our_products");
    private final By speakersImg = By.id("speakersImg");
    private final By speakersLink = By.id("speakersLink");
    private final By tabletsImg = By.id("tabletsImg");
    private final By tabletsLink = By.id("tabletsLink");
    private final By laptopsImg = By.id("laptopsImg");
    private final By laptopsLink = By.id("laptopsLink");
    private final By specialOfferItems = By.id("special_offer_items");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyIsOnHomePage() {
        this.waitUntilVisible(ourProducts);
        this.waitUntilVisible(specialOfferItems);
        log.info("Home page is displayed");
    }

    public void goToCategory(String category) {
        this.hoverOver(this.getCategoryImg(category));
        this.clickElement(this.getCategoryLink(category));
        // Takes you to CategoryPage
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
}
