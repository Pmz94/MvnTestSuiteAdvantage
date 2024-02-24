
// ContactUs
// Created by Mayra Juarez

package legacy.advantage.tsd;

import org.testng.annotations.Test;
import legacy.BaseClass;
import legacy.advantage.bsn.Bsn_ContactUs;

public class Tsd_ContactUs extends BaseClass {

	@Test
	public void contactUs() {
		Bsn_ContactUs contact = new Bsn_ContactUs(driver);
		contact.email = "team3@mail.com";
		contact.Run();
	}
}