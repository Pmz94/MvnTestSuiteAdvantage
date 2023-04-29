
// Login
// Created by Samantha Jasso

package pageobjects.advantage.tsd;

import org.testng.annotations.Test;
import base.BaseClass;
import pageobjects.advantage.bsn.Bsn_Login;

public class Tsd_Login extends BaseClass {

	@Test
	public void login() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();
	}
}