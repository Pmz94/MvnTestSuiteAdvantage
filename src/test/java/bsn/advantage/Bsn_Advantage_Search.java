
// Search
// Created by Walter Medina

package bsn.advantage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Bsn_Advantage_Search extends BasePage {

    public final String baseUrl = "https://advantageonlineshopping.com/";
    public final By searchIcon = By.id("searchSection");
    public final By searchInput = By.id("autoComplete");
    public final By viewAllOption = By.xpath("//a[@class='roboto-medium viewAll ng-scope']");
    public final By closeSearch = By.xpath("//div[@data-ng-click='closeSearchForce()']");
    public final String specificProduct = "//a[normalize-space()='%s']";
    public final By foundProduct = By.xpath("//a[@class='product ng-scope']//img");
    public final By productTitle = By.xpath("//h1[@class='roboto-regular screen768 ng-binding']");

    public Bsn_Advantage_Search(WebDriver driver) {
        super(driver);
    }

    public void searchSpecificProduct(String category, String specificProductName) {
        this.clickElement(searchIcon);
        this.inputData(searchInput, category);

        this.clickElement(viewAllOption);
        this.clickElement(closeSearch);

        String productName = this.getTextFromElement(By.xpath(String.format(specificProduct, specificProductName)));

        this.navigateTo(baseUrl);
        this.clickElement(searchIcon);
        this.inputData(searchInput, productName);
        this.clickElement(foundProduct);

        String productNameFound = this.getTextFromElement(productTitle);
        Assert.assertEquals(specificProductName.toUpperCase(), productNameFound);
    }
}
