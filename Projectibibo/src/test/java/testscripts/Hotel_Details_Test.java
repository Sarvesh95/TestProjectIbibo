package testscripts;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import frameworkUtility.FuncLibrary;
import pompages.Ibibo_Home;
import pompages.Ibibo_Hotel;
import pompages.Ibibo_Searchresult;

public class Hotel_Details_Test extends BaseTest{

	WebDriver driver;
	
	@Test
	public void testexecution() {
		driver = func.startBrowser();
		Ibibo_Home hm = new Ibibo_Home(driver);
		Ibibo_Searchresult sr = new Ibibo_Searchresult(driver);
		Ibibo_Hotel ht = new Ibibo_Hotel(driver);
		
		XSSFSheet testdatasheet	= excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("hotelsheet"));
		System.out.println("the data sheet found for hotel is "+testdatasheet);
		
		String location = excel.readStringCell(testdatasheet, 3, 2);
		
		func.launchURL();
		
		hm.searchHotels(location);
		String hotelTitle = sr.verify_first_Hotelname();
		
		try {
			ht.get_hotel_details("Delux_room");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		tearDown(driver);
		
	}
}
