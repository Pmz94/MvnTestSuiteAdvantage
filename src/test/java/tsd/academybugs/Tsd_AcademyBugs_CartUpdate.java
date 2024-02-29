package tsd.academybugs;

import base.BaseTest;
import org.testng.annotations.Test;
import bsn.academybugs.Bsn_AcademyBugs_CartUpdate;

public class Tsd_AcademyBugs_CartUpdate extends BaseTest {

	@Test
	public void cartUpdate() {
		Bsn_AcademyBugs_CartUpdate cartUpdate = new Bsn_AcademyBugs_CartUpdate(driver);
		cartUpdate.Run();
	}
}