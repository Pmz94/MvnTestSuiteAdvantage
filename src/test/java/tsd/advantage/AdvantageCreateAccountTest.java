
// CreateAccount
// Created by Maria Jose Rivera

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.pageobjects.CreateAccountPage;
import bsn.advantage.pageobjects.HomePage;
import java.io.IOException;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JacksonUtils;

public class AdvantageCreateAccountTest extends BaseTest {

    @Test
    public void createAccountWithOnlyRequiredFields() throws IOException {
        User user = JacksonUtils.deserializeJson("data/testUser.json", User.class);
        HomePage homePage = new HomePage(driver);
        homePage.verifyIsOnHomePage();
        homePage.goToCreateAccount();

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.verifyIsOnCreateAccountPage();
        createAccountPage.fillRequiredFields(user.getUsername(), user.getEmail(), user.getPassword());
        createAccountPage.clickRegisterButton();

        if (createAccountPage.isUserAlreadyExistsMessageVisible()) {
            log.info("User already exists, logging in");
            createAccountPage.goToHomePage();
            homePage.login(user.getUsername(), user.getPassword());
        }

        Assert.assertTrue(homePage.isUserLoggedIn(user.getUsername()), "User is not logged in");
    }
}