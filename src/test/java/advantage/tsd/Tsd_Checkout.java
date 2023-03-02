
// Checkout
// Created by Pedro Muñoz

package advantage.tsd;

import base.BaseClass;
import advantage.bsn.Bsn_AddingToCart;
import advantage.bsn.Bsn_Checkout;
import advantage.bsn.Bsn_Login;
import org.testng.annotations.Test;

public class Tsd_Checkout extends BaseClass {

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