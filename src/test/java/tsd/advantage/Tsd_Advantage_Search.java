
// Search
// Created by Walter Medina

package tsd.advantage;

import org.testng.annotations.Test;
import base.BaseTest;
import bsn.advantage.Bsn_Advantage_Search;

public class Tsd_Advantage_Search extends BaseTest {

    @Test
    public void search() {
        Bsn_Advantage_Search search = new Bsn_Advantage_Search(driver);
        String category = "speakers";
        String specificProductName = "Bose Soundlink Bluetooth Speaker III";
        search.searchSpecificProduct(category, specificProductName);
    }
}