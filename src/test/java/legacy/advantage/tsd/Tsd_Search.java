
// Search
// Created by Walter Medina

package legacy.advantage.tsd;

import org.testng.annotations.Test;
import legacy.BaseClass;
import legacy.advantage.bsn.Bsn_Search;

public class Tsd_Search extends BaseClass {
	@Test
	public void search() {
		Bsn_Search search = new Bsn_Search(driver);
		search.Run();
	}
}