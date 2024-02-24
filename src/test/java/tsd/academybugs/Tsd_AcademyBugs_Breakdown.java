package tsd.academybugs;

import base.Browsers;
import org.testng.annotations.Test;
import bsn.academybugs.Bsn_AcademyBugs_Breakdown;
public class Tsd_AcademyBugs_Breakdown extends Browsers {
	@Test
	public void Breakdown() {
		Bsn_AcademyBugs_Breakdown breakDown = new Bsn_AcademyBugs_Breakdown(driver);
		breakDown.Run();
	}
}