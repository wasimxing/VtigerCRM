package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * the class contains reusable methods to perfrom actions on excel  file
 * @author wasim
 *
 */
public class ExcelUtility {
	private Workbook wb;
/**
 * this method is used to initialize excel file
 * @param excelPath
 */
	public void excelInitialization(String excelPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}
/**
 * this method returns the data from excel file based on sheetName ,rowNum and cellNum provided
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @return
 */
	public String readDataFromExcel(String sheetName, int rowNum, int cellNum) {
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
/**
 * this method returns data from excel file based on sheetname and expectedTestName provided
 * @param sheetName
 * @param expectedTestName
 * @return
 */
	public Map<String, String> readDataFromExcel(String sheetName, String expectedTestName) {
		Map<String, String> map = new HashMap<>();
		Sheet sheet = wb.getSheet(sheetName);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			String actualTestName = sheet.getRow(i).getCell(1).getStringCellValue();
			if (actualTestName.equalsIgnoreCase(expectedTestName)) {
				for (int j = i; j <= sheet.getLastRowNum(); j++) {
					String key = sheet.getRow(j).getCell(2).getStringCellValue();
					String value = sheet.getRow(j).getCell(3).getStringCellValue();
					map.put(key, value);

					if (key.equals("####"))
						break;
				}
				break;
			}
		}
		return map;
	}
/**
 * this method is used to close the workbook
 */
	public void closeWorkbook() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * this method writes data to excel based on sheetname,rownum,cellnum,data,excel path provided
 * @param sheetName
 * @param rowNum
 * @param cellNum
 * @param data
 * @param excelPath
 */
	public void writeToExcel(String sheetName, int rowNum, int cellNum, String data, String excelPath) {
		Cell cell = wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * this method writes data to excel based on sheetname,status,expectedTestName,excelPath provided
 * @param sheetName
 * @param status
 * @param expectedTestName
 * @param excelPath
 */
	public void writeToExcel(String sheetName, String status, String expectedTestName, String excelPath) {
		Sheet sheet = wb.getSheet(sheetName);

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			String actualTestName = sheet.getRow(i).getCell(1).getStringCellValue();
			if (actualTestName.equalsIgnoreCase(expectedTestName)) {
				Cell cell = sheet.getRow(i).createCell(4);
				cell.setCellValue(status);
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(excelPath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					wb.write(fos);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
