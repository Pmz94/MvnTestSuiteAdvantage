
// Login
// Created by Samantha Jasso

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.pageobjects.HomePage;
import java.io.IOException;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JacksonUtils;

public class AdvantageLoginTest extends BaseTest {

    @Test
    public void loginWithValidCredentials() throws IOException {
        User user = JacksonUtils.deserializeJson("data/testUser.json", User.class);
        HomePage homePage = new HomePage(driver);
        homePage.verifyIsOnHomePage();
        homePage.login(user.getUsername(), user.getPassword());
        Assert.assertTrue(homePage.isUserLoggedIn(user.getUsername()), "User is not logged in");
    }
}