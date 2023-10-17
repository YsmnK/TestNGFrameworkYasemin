package com.neotech.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtility {
	private static Workbook book;
	private static Sheet sheet;
	
	public static void openExcel(String filePath) {
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadSheet(String sheetName) {
		sheet=book.getSheet(sheetName);
	}
	
	public static int rowCount() {
	
		return sheet.getPhysicalNumberOfRows();
		
	}

	public static int columnCount(int rowIndex) {
		
		return sheet.getRow(0).getLastCellNum();
	}	

	public static String cellData(int rowIndex ,int columnIndex) {
		return sheet.getRow(rowIndex).getCell(columnIndex).toString();
	}
	
	//Return excel sheet data into a 2D-Object Array
	
	public static Object[][] excelIntoArray(String filePath , String sheetName){
		
	openExcel(filePath);
	loadSheet(sheetName);
	int rowNumber=rowCount();
	int columnNumber=columnCount(0);
	
	Object [][] data=new Object[rowNumber-1][columnNumber];
	
	//iterate over rows
	for(int row=1;row<rowNumber;row++) {
		
		
		//iterate over columns
		for(int col=0;col<columnNumber;col++) {
			
			data[row-1][col]=cellData(row,col);
			
		}
	}
	//return the 2D array object
	return data;
	
	}
	
	@DataProvider(name="excelData")
	public Object[][] getExcelData() {
		String filePath=System.getProperty("user.dir")+ "/testdata/Excel.xlsx";
		String sheetName="Employe";
		return ExcelUtility.excelIntoArray(filePath, sheetName);
		
		
	}
}
