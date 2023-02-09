
// Contact
// Created by Mayra Juarez

package tsd;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_ContactUs;

public class Tsd_ContactUs extends Browsers {

	@Test
	public void contactUs() {
		Bsn_ContactUs contact = new Bsn_ContactUs(driver);
		contact.email = "team3@mail.com";
		contact.Run();
	}
}