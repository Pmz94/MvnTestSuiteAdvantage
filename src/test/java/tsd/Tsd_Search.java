
// Search
// Created by Walter Medina

package tsd;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_Search;

public class Tsd_Search extends Browsers {
    @Test
    public void search() {
        Bsn_Search search = new Bsn_Search(driver);
		search.Run();
    }
}
