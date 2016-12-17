package in.keys2javaselenium.newtours.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class XLReader {

	public int getGivenTestCaseRowCount(String testname)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream("./TestData" + "/NewToursData.xlsx");
		// Reading workbook object
		Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet s1 = wb.getSheet("Sheet1");
		int rowCount = s1.getPhysicalNumberOfRows();
		int count = 0;
		for (int rowindex = 1; rowindex < rowCount; rowindex++) {
			Row r = s1.getRow(rowindex);
			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			String secdata = c1.getStringCellValue();
			String thirdata = c2.getStringCellValue();

			if (thirdata.equalsIgnoreCase("Y") && secdata.equalsIgnoreCase(testname)) {
				count++;
			}
		}
		return count;
	}

	public int getGivenTestDataCellCount(String testcaseName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream("./TestData" + "/NewToursData.xlsx");
		// Reading workbook object
		Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet s1 = wb.getSheet("Sheet1");
		int rowCount = s1.getPhysicalNumberOfRows();

		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			Row r = s1.getRow(rowIndex);

			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			String secondCellData = c1.getStringCellValue();
			String thirdCellData = c2.getStringCellValue();

			if (thirdCellData.equalsIgnoreCase("Y") && secondCellData.equalsIgnoreCase(testcaseName)) {
				return r.getPhysicalNumberOfCells() - 3;
			}
		}
		return 0;
	}

	@DataProvider(name = "newtours")
	public String[][] storeCellDataForGivenTestCase(Method m)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(".\\TestData\\NewToursData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet s1 = wb.getSheet("Sheet1");

		String testcaseName = m.getName();
		int rowCount = s1.getPhysicalNumberOfRows();

		String[][] testData = new String[getGivenTestCaseRowCount(testcaseName)][getGivenTestDataCellCount(testcaseName)
				+ 1]; // 1
		int rindex = 0; // 2
		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			Row r = s1.getRow(rowIndex);

			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			int cIndex = 0; // 3

			String secondCellData = c1.getStringCellValue();
			String thirdCellData = c2.getStringCellValue();

			if (thirdCellData.equalsIgnoreCase("Y") && secondCellData.equalsIgnoreCase(testcaseName)) {
				for (int cellDataIndex = 3; cellDataIndex < r.getPhysicalNumberOfCells(); cellDataIndex++) {
					Cell c3 = r.getCell(cellDataIndex);
					testData[rindex][cIndex++] = c3.getStringCellValue(); // 4
				}
				testData[rindex][cIndex] = rowIndex + "";
				rindex++; // 5
			}
		}
		return testData; // 6
	}

	public static void writexl(String text, int rownum, int clonum)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream("./TestData/NewToursData.xlsx");
		Workbook w = WorkbookFactory.create(fis);
		Sheet s = w.getSheet("Sheet1");
		Row r = s.getRow(rownum);
		Cell c = r.createCell(clonum);
		c.setCellValue(text);

		FileOutputStream fos = new FileOutputStream("./TestData/NewToursData.xlsx");
		w.write(fos);
		fos.close();
		w.close();
	}
}
