package base;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

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