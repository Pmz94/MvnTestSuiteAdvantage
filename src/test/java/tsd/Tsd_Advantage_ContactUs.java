
// ContactUs
// Created by Mayra Juarez

package tsd;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_Advantage_ContactUs;

public class Tsd_Advantage_ContactUs extends Browsers {

	@Test
	public void contactUs() {
		Bsn_Advantage_ContactUs contact = new Bsn_Advantage_ContactUs(driver);
		contact.email = "team3@mail.com";
		contact.Run();
	}
}