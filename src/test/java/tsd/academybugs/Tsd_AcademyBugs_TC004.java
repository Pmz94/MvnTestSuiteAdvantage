package tsd.academybugs;

import base.Browsers;
import bsn.academybugs.Bsn_AcademyBugs_TC004;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_TC004 extends Browsers {

	@Test
	public void tc001() {
		Bsn_AcademyBugs_TC004 tc001 = new Bsn_AcademyBugs_TC004(driver);
		tc001.Run();
	}
}