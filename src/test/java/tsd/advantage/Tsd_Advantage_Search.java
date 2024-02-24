
// Search
// Created by Walter Medina

package tsd.advantage;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.advantage.Bsn_Advantage_Search;

public class Tsd_Advantage_Search extends Browsers {
	@Test
	public void search() {
		Bsn_Advantage_Search search = new Bsn_Advantage_Search(driver);
		search.Run();
	}
}