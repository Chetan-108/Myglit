package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;

public class DriverUtils extends BaseClass{

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static String path=null;
	
	public DriverUtils(String path) {
	 this.path=path;
	}
	public static String getScreenshot(String name) {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
	    String path=System.getProperty("user.dir")+"/screenshots/"+name+".jpg";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src,  dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		}
	public static void click(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elem));
		elem.click();
	}
	public static void sendKeys(WebElement elem, String value) {

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(elem));
		elem.click();
		elem.sendKeys(value);
	}
	public static int getRowCount(String file,String sheetName) throws Exception {
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowCount;
	}
	
	public static int getCellCount(String file,String sheetName,int rowNum) throws Exception {
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}
	
	public static String getCellData(String file,String sheetName,int rowNum,int colNum) throws Exception {
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
	    cell=row.getCell(colNum);
	  
	    DataFormatter df=new DataFormatter();
	    String data;
	    try {
	    data=df.formatCellValue(cell);
	    }
	    catch(Exception e) {
	    	data="";
	    }
		workbook.close();
		fis.close();
		return data;
	}
	
	public void SetCellData(String file,String sheetName,int rowNum,int colNum) throws Exception {
		fis=new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
	    cell=row.getCell(colNum);
	    fos=new FileOutputStream(path);
	    workbook.write(fos);
	    
		workbook.close();
		fis.close();
		fos.close();
	}
}
