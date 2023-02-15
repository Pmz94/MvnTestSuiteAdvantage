package tsd;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_AcademyBugs_CheckoutProcess;

public class Tsd_AcademyBugs_CheckoutProcess extends Browsers {

	@Test
	public void Checkout() {
		Bsn_AcademyBugs_CheckoutProcess checkOut = new Bsn_AcademyBugs_CheckoutProcess(driver);
		checkOut.Run();
	}
}