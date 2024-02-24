package tsd.academybugs;

import base.Browsers;
import bsn.academybugs.Bsn_AcademyBugs_TC021;
import org.testng.annotations.Test;

public class Tsd_AcademyBugs_TC021 extends Browsers {

	@Test
	public void tc021() {
		Bsn_AcademyBugs_TC021 tc021 = new Bsn_AcademyBugs_TC021(driver);
		tc021.Run();
	}
}