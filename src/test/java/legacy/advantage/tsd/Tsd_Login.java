
// Login
// Created by Samantha Jasso

package legacy.advantage.tsd;

import org.testng.annotations.Test;
import legacy.BaseClass;
import legacy.advantage.bsn.Bsn_Login;

public class Tsd_Login extends BaseClass {

	@Test
	public void login() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();
	}
}