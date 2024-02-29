package tsd.academybugs;

import base.BaseTest;
import bsn.academybugs.Bsn_AcademyBugs_TC001;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_TC001 extends BaseTest {

	@Test
	public void tc001() {
		Bsn_AcademyBugs_TC001 tc001 = new Bsn_AcademyBugs_TC001(driver);
		tc001.Run();
	}
}
