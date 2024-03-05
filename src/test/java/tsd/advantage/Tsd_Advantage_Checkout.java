
// Checkout
// Created by Pedro Muñoz

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.Bsn_Advantage_AddingToCart;
import bsn.advantage.Bsn_Advantage_Checkout;
import bsn.advantage.Bsn_Advantage_Login;
import org.testng.annotations.Test;

public class Tsd_Advantage_Checkout extends BaseTest {

    @Test
    public void checkout() {
        Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
        String user = "Team003";
        String password = "Team3";
        login.login(user, password);
        login.verifyUserLoggedIn(user);

        Bsn_Advantage_AddingToCart addCart = new Bsn_Advantage_AddingToCart(driver);
        addCart.addToCartBySpecificName("speakers", "Bose Soundlink Bluetooth Speaker III");
        addCart.addToCartByFirstItemOnTheList("tablets");
        addCart.addToCartBySpecificName("laptops", "HP Chromebook 14 G1(ENERGY STAR)");

        Bsn_Advantage_Checkout checkout = new Bsn_Advantage_Checkout(driver);
        checkout.Run(user, password);
    }
}