package bsn.advantage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends NavbarPage {

    private final By pageTitle = By.cssSelector("h3.categoryTitle");
    private final By firstItemOnTheList = By.cssSelector("ul > .ng-scope:nth-child(1)");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public void verifyIsOnCategoryPage() {
        this.waitUntilVisible(pageTitle);
        log.info("Category page is displayed, " + this.getTextFromElement(pageTitle));
    }

    public void clickOnFirstProduct() {
        this.clickElement(firstItemOnTheList);
        // Takes you to ProductPage
    }

    public void clickOnProduct(String productName) {
        this.clickElement(By.linkText(productName));
        // Takes you to ProductPage
    }
}
