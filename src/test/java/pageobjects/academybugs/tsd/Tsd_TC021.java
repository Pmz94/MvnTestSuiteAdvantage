package pageobjects.academybugs.tsd;

import base.BaseClass;
import pageobjects.academybugs.bsn.Bsn_TC021;
import org.testng.annotations.Test;

public class Tsd_TC021 extends BaseClass {

	@Test
	public void tc021() {
		Bsn_TC021 tc021 = new Bsn_TC021(driver);
		tc021.Run();
	}
}