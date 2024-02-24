
// Checkout
// Created by Pedro Muñoz

package tsd.advantage;

import base.Browsers;
import bsn.advantage.Bsn_Advantage_AddingToCart;
import bsn.advantage.Bsn_Advantage_Checkout;
import bsn.advantage.Bsn_Advantage_Login;
import org.testng.annotations.Test;

public class Tsd_Advantage_Checkout extends Browsers {

	@Test
	public void checkout() {
		Bsn_Advantage_Login login = new Bsn_Advantage_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();

		Bsn_Advantage_AddingToCart addCart = new Bsn_Advantage_AddingToCart(driver);
		addCart.Run();

		Bsn_Advantage_Checkout checkout = new Bsn_Advantage_Checkout(driver);
		checkout.safepay_user = "Team003";
		checkout.safepay_password = "Team3";
		checkout.Run();
	}
}