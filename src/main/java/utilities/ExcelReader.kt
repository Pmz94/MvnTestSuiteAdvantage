package utilities

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream

class ExcelReader {

	fun getCellData(filePath: String, sheetName: String): Array<Array<String?>> {
		val stream = FileInputStream(filePath)
		val workbook = WorkbookFactory.create(stream)
		val s = workbook.getSheet(sheetName)
		val rowcount = s.lastRowNum
		val cellcount = s.getRow(0).lastCellNum.toInt()
		val data = Array(rowcount) { arrayOfNulls<String>(cellcount) }

		for(i in 1..rowcount) {
			val r = s.getRow(i)
			for(j in 0 until cellcount) {
				val c = r.getCell(j)
				try {
					if(c.cellType == CellType.STRING) {
						data[i - 1][j] = c.stringCellValue
					} else {
						data[i - 1][j] = c.numericCellValue.toString()
					}
				} catch(e: Exception) {
					e.printStackTrace()
				}
			}
		}
		return data
	}
}
/*
public class ExcelReader {

	public String[][] getCellData(String path, String sheetName) throws IOException {
		FileInputStream stream = new FileInputStream(path);
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet s = workbook.getSheet(sheetName);
		int rowcount = s.getLastRowNum();
		int cellcount = s.getRow(0).getLastCellNum();
		String[][] data = new String[rowcount][cellcount];

		for(int i = 1; i <= rowcount; i++) {
			Row r = s.getRow(i);
			for(int j = 0; j < cellcount; j++) {
			Cell c = r.getCell(j);
			try {
				if(c.getCellType() == CellType.STRING) {
					data[i - 1][j] = c.getStringCellValue();
				} else {
					data[i - 1][j] = String.valueOf(c.getNumericCellValue());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		}

		return data;
	}
}
*/