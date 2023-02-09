
// Checkout
// Created by Pedro Muñoz

package tsd;

import java.io.IOException;

import bsn.Bsn_AddingToCart;
import bsn.Bsn_Login;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.Browsers;
import base.ExcelReader;
import bsn.Bsn_Checkout;

public class Tsd_Checkout extends Browsers {

	@Test
	public void checkout() {
		Bsn_Login login = new Bsn_Login(driver);
		login.user = "Team003";
		login.password = "Team3";
		login.Run();

		Bsn_AddingToCart addCart = new Bsn_AddingToCart(driver);
		addCart.Run();

		Bsn_Checkout checkout = new Bsn_Checkout(driver);
		checkout.safepay_user = "Team003";
		checkout.safepay_password = "Team3";
		checkout.Run();
	}

	// @DataProvider(name = "datatest")
	// public Object[][] dp() {
	// 	return new Object[][] { new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
	// 		new Object[] { "PedroTorresL40", "hola123", "ptorres@example.com" },
	// 		new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
	// 		new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" },
	// 		new Object[] { "PedroMontes81", "hola123", "pmontes@example.com" } };
	// }
	//
	// @DataProvider(name = "datatestexcel")
	// public String[][] getExcelData() throws InvalidFormatException, IOException {
	// 	ExcelReader read = new ExcelReader();
	// 	String file = "C:\\Users\\pedro.munozz\\Documents\\EclipseProjects\\Selenium\\Data\\users.xlsx";
	// 	return read.getCellData(file, "Sheet1");
	// }
}