
// Search
// Created by Walter Medina

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.pageobjects.HomePage;
import bsn.advantage.pageobjects.ProductPage;
import bsn.advantage.pageobjects.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvantageSearchTest extends BaseTest {

    @Test
    public void searchFirstProductFromCategory() {
        HomePage homePage = new HomePage(driver);
        homePage.verifyIsOnHomePage();
        homePage.searchByCategory("speakers");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.verifyIsOnSearchResultsPage();
        String productName = searchResultsPage.getFirstProductName();
        searchResultsPage.goToHomePage();
        homePage.searchBySpecificProduct(productName);

        ProductPage productPage = new ProductPage(driver);
        productPage.verifyIsOnProductPage();
        String productNameFound = productPage.getProductName();
        Assert.assertEquals(productName.toUpperCase(), productNameFound, "Product name does not match.");
    }

    @Test
    public void searchSpecificProduct() {
        String expectedProductName = "Bose Soundlink Bluetooth Speaker III";
        HomePage homePage = new HomePage(driver);
        homePage.verifyIsOnHomePage();
        homePage.searchBySpecificProduct(expectedProductName);

        ProductPage productPage = new ProductPage(driver);
        productPage.verifyIsOnProductPage();
        String productNameFound = productPage.getProductName();
        Assert.assertEquals(productNameFound, expectedProductName.toUpperCase(), "Product name does not match.");
    }
}