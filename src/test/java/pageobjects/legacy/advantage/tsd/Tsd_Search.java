
// Search
// Created by Walter Medina

package pageobjects.legacy.advantage.tsd;

import org.testng.annotations.Test;
import base.BaseClass;
import pageobjects.legacy.advantage.bsn.Bsn_Search;

public class Tsd_Search extends BaseClass {
	@Test
	public void search() {
		Bsn_Search search = new Bsn_Search(driver);
		search.Run();
	}
}