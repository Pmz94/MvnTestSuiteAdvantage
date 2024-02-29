package tsd.academybugs;

import org.testng.annotations.Test;
import base.BaseTest;
import bsn.academybugs.Bsn_AcademyBugs_CheckoutProcess;

public class Tsd_AcademyBugs_CheckoutProcess extends BaseTest {

	@Test
	public void Checkout() {
		Bsn_AcademyBugs_CheckoutProcess checkOut = new Bsn_AcademyBugs_CheckoutProcess(driver);
		checkOut.Run();
	}
}