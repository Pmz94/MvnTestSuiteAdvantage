package legacy.academybugs.tsd;

import legacy.BaseClass;
import legacy.academybugs.bsn.Bsn_TC004;
import org.testng.annotations.Test;

public class Tsd_TC004 extends BaseClass {

	@Test
	public void tc001() {
		Bsn_TC004 tc001 = new Bsn_TC004(driver);
		tc001.Run();
	}
}