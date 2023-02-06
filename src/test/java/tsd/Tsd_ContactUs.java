
// Contact
// Created by Mayra Juarez

package tsd;

import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_ContactUs;

public class Tsd_ContactUs extends Browsers {

	@Test
	public void login() {
		Bsn_ContactUs login = new Bsn_ContactUs(driver);
		login.Run();
	}
}