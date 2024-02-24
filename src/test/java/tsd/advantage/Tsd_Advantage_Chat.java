
// Chat
// Created by Marcelo Gonzalez

package tsd.advantage;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.Browsers;
import bsn.advantage.Bsn_Advantage_Chat;

public class Tsd_Advantage_Chat extends Browsers {

	@Test
	public void Chat() {
		Bsn_Advantage_Chat chat = new Bsn_Advantage_Chat(driver);
		boolean isCheck = chat.chatOpen();
		Assert.assertTrue(isCheck);
	}
}