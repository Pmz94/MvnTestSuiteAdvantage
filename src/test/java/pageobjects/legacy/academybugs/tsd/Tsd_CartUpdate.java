package pageobjects.legacy.academybugs.tsd;

import base.BaseClass;
import org.testng.annotations.Test;
import pageobjects.legacy.academybugs.bsn.Bsn_CartUpdate;

public class Tsd_CartUpdate extends BaseClass {

	@Test
	public void cartUpdate() {
		Bsn_CartUpdate cartUpdate = new Bsn_CartUpdate(driver);
		cartUpdate.Run();
	}
}