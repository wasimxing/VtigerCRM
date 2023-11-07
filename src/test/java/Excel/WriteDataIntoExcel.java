package Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		Map <String,String> map= new HashMap<>();
		
		map.put("Wasim", "Raja");
		
		FileInputStream fis = new FileInputStream("./src/test/resources/sheet.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh= wb.getSheet("Sheet1");
		Row row = sh.createRow(0);
		Cell cell=row.createCell(1);
		cell.setCellValue("Wasim");
		row.createCell(2).setCellValue("raja");
		
		
		
		
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/sheet.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
