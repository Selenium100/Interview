/**
 * 
 */
package com.utility.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	FileInputStream fis;
	XSSFWorkbook ExcelWBook;
	XSSFSheet ExcelWSheet;
	XSSFCell Cell;

	
	public ExcelUtils(String path, String sheetname)  {
		
		try {
		fis = new FileInputStream(path);
		 ExcelWBook = new XSSFWorkbook(fis);
		 ExcelWSheet = ExcelWBook.getSheet(sheetname);
		}catch(Exception e) {
			e.getMessage();
		}
	}
	

	public String getCellData(int RowNum, int ColNum) throws Exception {

	
		  
			 Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				String CellData = Cell.getStringCellValue();
				return CellData;
			
	}
	
	public  Object[][] getTableArray() throws Exception {

		String[][] tabArray = null;
		try {

			

			int startRow = 1;
			int startCol = 0;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();

			// Getting the column count
			int totalCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			tabArray = new String[totalRows][totalCols];
			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return (tabArray);

	}

	

}
