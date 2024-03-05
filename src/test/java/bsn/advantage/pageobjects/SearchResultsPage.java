package bsn.advantage.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends NavbarPage {

    private final By pageTitle = By.id("searchResultLabel");
    private final By productName = By.cssSelector("li a.productName");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyIsOnSearchResultsPage() {
        this.waitUntilVisible(pageTitle);
        log.info(this.getTextFromElement(pageTitle));
    }

    public String getFirstProductName() {
        return this.getTextFromElement(productName);
    }
}
