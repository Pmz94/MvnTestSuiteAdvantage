package pageobjects.academybugs.tsd;

import base.BaseClass;
import org.testng.annotations.Test;
import pageobjects.academybugs.bsn.Bsn_Breakdown;

public class Tsd_Breakdown extends BaseClass {

	@Test
	public void Breakdown() {
		Bsn_Breakdown breakDown = new Bsn_Breakdown(driver);
		breakDown.Run();
	}
}