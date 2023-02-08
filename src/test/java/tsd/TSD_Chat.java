package tsd;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.Browsers;
//import base.Navegadores;
import bsn.BSN_Chat;

public class TSD_Chat extends Browsers {
	
	@Test
	public void Chat() throws InterruptedException {
		BSN_Chat chat = new BSN_Chat(driver);
		boolean isCheck = chat.chatOpen();
		Assert.assertEquals(isCheck, true);
		
	}
}
