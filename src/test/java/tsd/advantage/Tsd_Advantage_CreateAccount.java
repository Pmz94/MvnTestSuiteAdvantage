
// CreateAccount
// Created by Maria Jose Rivera

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.Bsn_Advantage_CreateAccount;
import java.io.IOException;
import models.User;
import org.testng.annotations.Test;
import utils.JacksonUtils;

public class Tsd_Advantage_CreateAccount extends BaseTest {

    @Test
    public void createAccount() throws IOException {
        User user = JacksonUtils.deserializeJson("data/testUser.json", User.class);
        Bsn_Advantage_CreateAccount createAccountPage = new Bsn_Advantage_CreateAccount(driver);
        createAccountPage.createAccount(user.getUsername(), user.getEmail(), user.getPassword());
    }
}