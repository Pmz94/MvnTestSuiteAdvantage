
// Chat
// Created by Marcelo Gonzalez

package pageobjects.advantage.tsd;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseClass;
import pageobjects.advantage.bsn.Bsn_Chat;

public class Tsd_Chat extends BaseClass {

	@Test
	public void Chat() {
		Bsn_Chat chat = new Bsn_Chat(driver);
		boolean isCheck = chat.chatOpen();
		Assert.assertTrue(isCheck);
	}
}