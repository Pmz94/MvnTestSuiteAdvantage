package academybugs.tsd;

import org.testng.annotations.Test;
import base.BaseClass;
import academybugs.bsn.Bsn_CheckoutProcess;

public class Tsd_CheckoutProcess extends BaseClass {

	@Test
	public void Checkout() {
		Bsn_CheckoutProcess checkOut = new Bsn_CheckoutProcess(driver);
		checkOut.Run();
	}
}