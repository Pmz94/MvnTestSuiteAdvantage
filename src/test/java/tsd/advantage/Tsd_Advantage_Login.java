
// Login
// Created by Samantha Jasso

package tsd.advantage;

import base.BaseTest;
import bsn.advantage.Bsn_Advantage_Login;
import java.io.IOException;
import models.User;
import org.testng.annotations.Test;
import utils.JacksonUtils;

public class Tsd_Advantage_Login extends BaseTest {

    @Test
    public void login() throws IOException {
        User user = JacksonUtils.deserializeJson("data/testUser.json", User.class);
        Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
        login.login(user.getUsername(), user.getPassword());
        login.verifyUserLoggedIn(user.getUsername());
    }
}