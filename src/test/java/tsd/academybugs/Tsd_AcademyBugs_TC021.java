package tsd.academybugs;

import base.BaseTest;
import bsn.academybugs.Bsn_AcademyBugs_TC021;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_TC021 extends BaseTest {

	@Test
	public void tc021() {
		Bsn_AcademyBugs_TC021 tc021 = new Bsn_AcademyBugs_TC021(driver);
		tc021.Run();
	}
}