
// Login
// Created by Samantha Jasso

package tsd;
import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_Login;

public class Tsd_Login extends Browsers {

	@Test
	public void login() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();
	}
}