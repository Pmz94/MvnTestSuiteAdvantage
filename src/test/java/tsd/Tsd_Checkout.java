
// Checkout
// Created by Pedro Muñoz

package tsd;

import base.Browsers;
import bsn.Bsn_AddingToCart;
import bsn.Bsn_Checkout;
import bsn.Bsn_Login;
import org.testng.annotations.Test;

public class Tsd_Checkout extends Browsers {

	@Test
	public void checkout() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();

		Bsn_AddingToCart addCart = new Bsn_AddingToCart(driver);
		addCart.Run();

		Bsn_Checkout checkout = new Bsn_Checkout(driver);
		checkout.safepay_user = "Team003";
		checkout.safepay_password = "Team3";
		checkout.Run();
	}
}