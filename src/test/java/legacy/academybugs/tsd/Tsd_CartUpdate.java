package legacy.academybugs.tsd;

import legacy.BaseClass;
import legacy.academybugs.bsn.Bsn_CartUpdate;
import org.testng.annotations.Test;

public class Tsd_CartUpdate extends BaseClass {

	@Test
	public void cartUpdate() {
		Bsn_CartUpdate cartUpdate = new Bsn_CartUpdate(driver);
		cartUpdate.Run();
	}
}