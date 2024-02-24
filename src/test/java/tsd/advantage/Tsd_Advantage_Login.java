
// Login
// Created by Samantha Jasso

package tsd.advantage;
import org.testng.annotations.Test;
import base.Browsers;
import bsn.advantage.Bsn_Advantage_Login;

public class Tsd_Advantage_Login extends Browsers {

	@Test
	public void login() {
		Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();
	}
}