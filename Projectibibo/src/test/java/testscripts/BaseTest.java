package testscripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import excelAndreports.ExcelReader;
import frameworkUtility.FuncLibrary;
import frameworkUtility.FuncUtilLibrary;

public class BaseTest {
	
	WebDriver driver;
	
	FuncLibrary func = new FuncLibrary();
	
	ExcelReader excel = new ExcelReader();
	
	
	
	FuncUtilLibrary futil = new FuncUtilLibrary();

	
	@BeforeTest
	public void setUP() {
		
	
              futil.loadProperties();
              
		
	}
	
	@AfterTest
	public void tearDown(WebDriver driver) {
		
		driver.close();
		
		driver.quit();
	}
}
