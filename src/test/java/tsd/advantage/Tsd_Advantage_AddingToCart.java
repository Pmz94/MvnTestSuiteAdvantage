
// AddingToCart
// Created by Samuel Arriola

package tsd.advantage;

import bsn.advantage.Bsn_Advantage_Login;
import java.io.IOException;
import models.User;
import org.testng.annotations.Test;
import base.BaseTest;
import bsn.advantage.Bsn_Advantage_AddingToCart;
import utils.JacksonUtils;

public class Tsd_Advantage_AddingToCart extends BaseTest {

    @Test
    public void addingToCart() throws IOException {
        User user = JacksonUtils.deserializeJson("data/testUser.json", User.class);
        Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
        login.login(user.getUsername(), user.getPassword());
        login.verifyUserLoggedIn(user.getUsername());

        Bsn_Advantage_AddingToCart addCart = new Bsn_Advantage_AddingToCart(driver);
        addCart.Run();
    }
}