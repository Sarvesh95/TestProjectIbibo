package testscripts;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import excelAndreports.ExcelReader;
import frameworkUtility.FuncLibrary;
import frameworkUtility.FuncUtilLibrary;
import pompages.Ibibo_Home;
import pompages.Ibibo_Searchresult;

public class Search_Hotel_Test extends BaseTest{

	
	String TestData = null;
	
	
	
	

	@Test(retryAnalyzer = RetryAnalyser.class)
	public void testExecution() {
		
		
		
	    XSSFSheet testdatasheet	= excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("hotelsheet")); //getting the object of the sheet
	    
	   TestData = excel.readStringCell(testdatasheet, 1, 2); // extracting the data from excelsheet
	   
		func.launchURL();
		Ibibo_Home hm = new Ibibo_Home(driver);
		Ibibo_Searchresult sr = new Ibibo_Searchresult(driver);
		
		hm.searchHotels(TestData);
		sr.Hotels_found();
		
		
		
		
		
		
	}
	
	
}
