
// Chat
// Created by Marcelo Gonzalez

package legacy.advantage.tsd;

import legacy.advantage.bsn.Bsn_Chat;
import org.testng.Assert;
import org.testng.annotations.Test;
import legacy.BaseClass;

public class Tsd_Chat extends BaseClass {

	@Test
	public void Chat() {
		Bsn_Chat chat = new Bsn_Chat(driver);
		boolean isCheck = chat.chatOpen();
		Assert.assertTrue(isCheck);
	}
}