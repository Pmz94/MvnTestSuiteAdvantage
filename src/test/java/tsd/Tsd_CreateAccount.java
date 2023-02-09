
// CreateAccount
// Created by Maria Jose Rivera

package tsd;

import org.testng.annotations.Test;

import base.Browsers;
import bsn.Bsn_CreateAccount;

public class Tsd_CreateAccount extends Browsers {

	@Test
	public void CreateAccount() {
		Bsn_CreateAccount user = new Bsn_CreateAccount(driver);
		user.user = "Team003";
		user.email = "team3@mail.com";
		user.password = "Team3";
		user.Run();
	}
}