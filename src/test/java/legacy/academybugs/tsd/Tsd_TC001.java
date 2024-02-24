package legacy.academybugs.tsd;

import legacy.BaseClass;
import legacy.academybugs.bsn.Bsn_TC001;
import org.testng.annotations.Test;

public class Tsd_TC001 extends BaseClass {

	@Test
	public void tc001() {
		Bsn_TC001 tc001 = new Bsn_TC001(driver);
		tc001.Run();
	}
}
