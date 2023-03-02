
// CreateAccount
// Created by Maria Jose Rivera

package advantage.tsd;

import org.testng.annotations.Test;
import base.BaseClass;
import advantage.bsn.Bsn_CreateAccount;

public class Tsd_CreateAccount extends BaseClass {

	@Test
	public void CreateAccount() {
		Bsn_CreateAccount user = new Bsn_CreateAccount(driver);
		user.user = "Team003";
		user.email = "team3@mail.com";
		user.password = "Team3";
		user.Run();
	}
}