
// Checkout
// Created by Pedro Muñoz

package tsd;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.Browsers;
import base.ExcelReader;
import bsn.Bsn_Checkout;

public class Tsd_Checkout extends Browsers {

	@Test(dataProvider = "datatestexcel")
	public void checkout(String username, String passwd, String email) {
		print(username);
		print(passwd);
		print(email);
		Bsn_Checkout checkout = new Bsn_Checkout(driver);
		checkout.Run();
	}

	@DataProvider(name = "datatest")
	public Object[][] dp() {
		return new Object[][] { new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
			new Object[] { "PedroTorresL40", "hola123", "ptorres@example.com" },
			new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
			new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
			new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" } };
	}

	@DataProvider(name = "datatestexcel")
	public String[][] getExcelData() throws InvalidFormatException, IOException {
		ExcelReader read = new ExcelReader();
		String file = "C:\\Users\\pedro.munozz\\Documents\\EclipseProjects\\Selenium\\Data\\users.xlsx";
		return read.getCellData(file, "Sheet1");
	}
}