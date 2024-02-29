
// ContactUs
// Created by Mayra Juarez

package tsd.advantage;

import org.testng.annotations.Test;
import base.BaseTest;
import bsn.advantage.Bsn_Advantage_ContactUs;

public class Tsd_Advantage_ContactUs extends BaseTest {

	@Test
	public void contactUs() {
		Bsn_Advantage_ContactUs contact = new Bsn_Advantage_ContactUs(driver);
		String email = "team3@mail.com";
		String category = "Mice";
		String product = "HP Z4000 Wireless Mouse";
		String subject = "Producto defectuoso";
		contact.Run(category, product, email, subject);
	}
}