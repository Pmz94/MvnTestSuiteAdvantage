package tsd;

import base.Browsers;
import org.testng.annotations.Test;
import bsn.Bsn_AcademyBugs_CartUpdate;

public class Tsd_AcademyBugs_CartUpdate extends Browsers {

	@Test
	public void cartUpdate() {
		Bsn_AcademyBugs_CartUpdate cartUpdate = new Bsn_AcademyBugs_CartUpdate(driver);
		cartUpdate.Run();
	}
}