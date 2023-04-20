package testscripts;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pompages.Ibibo_Home;
import pompages.Ibibo_Searchresult;

public class Hotel_search_with_filters extends BaseTest {

	WebDriver driver;
	
	@Test
	public void testexecution() {
		 driver = func.startBrowser();
		Ibibo_Home hm = new Ibibo_Home(driver);
		Ibibo_Searchresult sr = new Ibibo_Searchresult(driver);
		
		XSSFSheet sheet = excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("hotelsheet"));
		
		String location = excel.readStringCell(sheet, 4, 2);
		
		func.launchURL();
		
		hm.searchHotels(location);
		
		sr.Hotels_found();
		
		sr.apply_and_verifyFilters();
		
		tearDown(driver);
		 
	}
}
