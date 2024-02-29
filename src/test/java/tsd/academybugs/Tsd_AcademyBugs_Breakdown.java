package tsd.academybugs;

import base.BaseTest;
import org.testng.annotations.Test;
import bsn.academybugs.Bsn_AcademyBugs_Breakdown;
public class Tsd_AcademyBugs_Breakdown extends BaseTest {
	@Test
	public void Breakdown() {
		Bsn_AcademyBugs_Breakdown breakDown = new Bsn_AcademyBugs_Breakdown(driver);
		breakDown.Run();
	}
}