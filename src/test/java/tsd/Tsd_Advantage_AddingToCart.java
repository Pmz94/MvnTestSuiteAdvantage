
// AddingToCart
// Created by Samuel Arriola

package tsd;

import bsn.Bsn_Advantage_Login;
import org.testng.annotations.Test;

import base.Browsers;
import bsn.Bsn_Advantage_AddingToCart;

public class Tsd_Advantage_AddingToCart extends Browsers {
	@Test
	public void addingToCart() {
		Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();

		Bsn_Advantage_AddingToCart addCart = new Bsn_Advantage_AddingToCart(driver);
		addCart.Run();
	}
}