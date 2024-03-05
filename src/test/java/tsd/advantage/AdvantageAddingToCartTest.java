
// AddingToCart
// Created by Samuel Arriola

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.pageobjects.CartPage;
import bsn.advantage.pageobjects.CategoryPage;
import bsn.advantage.pageobjects.HomePage;
import bsn.advantage.pageobjects.ProductPage;
import java.io.IOException;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JacksonUtils;

public class AdvantageAddingToCartTest extends BaseTest {

    @Test
    public void addProductToCart() {
        String[][] categories = {
            {"speakers", "Bose Soundlink Bluetooth Speaker III"},
            {"laptops", "HP Chromebook 14 G1(ENERGY STAR)"}
        };

        HomePage homePage = new HomePage(driver);

        for (String[] category : categories) {
            homePage.goToCategory(category[0]);
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryPage.verifyIsOnCategoryPage();
            categoryPage.clickOnProduct(category[1]);

            ProductPage productPage = new ProductPage(driver);
            productPage.verifyIsOnProductPage();
            Assert.assertEquals(productPage.getProductName(), category[1].toUpperCase(), "Product name is not correct");
            productPage.addToCart();
            productPage.goToHomePage();
        }

        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.verifyIsOnCartPage();
        cartPage.removeAllProductsFromCart();
    }

    @Test
    public void addFirstProductToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.goToCategory("tablets");

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.verifyIsOnCategoryPage();
        categoryPage.clickOnFirstProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.verifyIsOnProductPage();
        String productName = productPage.getProductName();
        productPage.addToCart();
        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.verifyIsOnCartPage();
        cartPage.removeProductFromCart(productName);
    }
}