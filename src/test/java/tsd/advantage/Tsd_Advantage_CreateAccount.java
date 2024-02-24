
// CreateAccount
// Created by Maria Jose Rivera

package tsd.advantage;

import org.testng.annotations.Test;

import base.Browsers;
import bsn.advantage.Bsn_Advantage_CreateAccount;

public class Tsd_Advantage_CreateAccount extends Browsers {

	@Test
	public void CreateAccount() {
		Bsn_Advantage_CreateAccount user = new Bsn_Advantage_CreateAccount(driver);
		user.user = "Team003";
		user.email = "team3@mail.com";
		user.password = "Team3";
		user.Run();
	}
}