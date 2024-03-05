
// Search
// Created by Walter Medina

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Bsn_Advantage_Search extends BasePage {

    private final By advantageLogo = By.cssSelector("a[ng-click='go_up()']");
    private final By searchIcon = By.id("searchSection");
    private final By searchInput = By.id("autoComplete");
    private final By viewAllBtn = By.xpath("//a[@class='roboto-medium viewAll ng-scope']");
    private final By closeSearch = By.xpath("//div[@data-ng-click='closeSearchForce()']");
    private final String specificProduct = "//a[normalize-space()='%s']";
    private final By foundProduct = By.xpath("//a[@class='product ng-scope']//img");
    private final By productTitle = By.xpath("//h1[@class='roboto-regular screen768 ng-binding']");

    public Bsn_Advantage_Search(WebDriver driver) {
        super(driver);
    }

    public void searchSpecificProduct(String category, String specificProductName) {
        // Home page
        this.clickElement(searchIcon);
        this.inputData(searchInput, category);
        // Search results page
        this.clickElement(viewAllBtn);
        this.clickElement(closeSearch);

        String productName = this.getTextFromElement(By.xpath(String.format(specificProduct, specificProductName)));

        this.clickElement(advantageLogo);

        // Home page
        this.clickElement(searchIcon);
        this.inputData(searchInput, productName);
        this.clickElement(foundProduct);

        // Product page
        String productNameFound = this.getTextFromElement(productTitle);
        Assert.assertEquals(specificProductName.toUpperCase(), productNameFound);
    }
}
