
// AddingToCart
// Created by Samuel Arriola

package pageobjects.legacy.advantage.tsd;

import pageobjects.legacy.advantage.bsn.Bsn_AddingToCart;
import pageobjects.legacy.advantage.bsn.Bsn_Login;
import org.testng.annotations.Test;
import base.BaseClass;

public class Tsd_AddingToCart extends BaseClass {
	@Test
	public void addingToCart() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();

		Bsn_AddingToCart addCart = new Bsn_AddingToCart(driver);
		addCart.Run();
	}
}