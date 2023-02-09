package tsd;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.Browsers;
import bsn.Bsn_Chat;

public class Tsd_Chat extends Browsers {

	@Test
	public void Chat() {
		Bsn_Chat chat = new Bsn_Chat(driver);
		boolean isCheck = chat.chatOpen();
		Assert.assertTrue(isCheck);
	}
}