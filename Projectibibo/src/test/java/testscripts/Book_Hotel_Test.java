package testscripts;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import frameworkUtility.FuncUtilLibrary;
import pompages.Ibibo_Home;
import pompages.Ibibo_Hotel;
import pompages.Ibibo_Payment;
import pompages.Ibibo_Searchresult;

public class Book_Hotel_Test extends BaseTest {

	WebDriver driver;
	String TestData ;
	
	@Test
	public void testExecution1() {
		
		driver = func.startBrowser();
		
		
		
	    XSSFSheet testdatasheet	= excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("hotelsheet"));//getting the object of the sheet
	    System.out.println("the data sheet found for hotel is "+testdatasheet);
	    XSSFSheet guestdata = excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("guestsheet"));
	    System.out.println("the data sheet found for guest is "+guestdata);
	    XSSFSheet guestdatasheet = excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("Guestsheet"));
	    System.out.println("the data sheet found for guest details is "+guestdatasheet);
	    XSSFSheet paymentdatasheet = excel.getWorksheet(futil.readProperty("TestDataSheet"), futil.readProperty("creditsheet"));
	    
	   String location = excel.readStringCell(testdatasheet, 2, 2); // extracting the data from excelsheet
	   String hotelname = excel.readStringCell(testdatasheet, 2, 3);
	 /*  String checkin = excel.readStringCell(testdatasheet, 2, 4);
	   String checkout = excel.readStringCell(testdatasheet, 2, 5);
	   String GuestandRooms = excel.readStringCell(testdatasheet, 2, 6);*/
	   
		func.launchURL();
		Ibibo_Home hm = new Ibibo_Home(driver);
		Ibibo_Searchresult sr = new Ibibo_Searchresult(driver);
		Ibibo_Hotel ho = new Ibibo_Hotel(driver);
		Ibibo_Payment py = new Ibibo_Payment(driver);
		hm.searchHotels(location);
		String hotelTitle = sr.verify_Hotelname();
		
		ho.book_and_verify_Hotel( hotelname, hotelTitle);	
			
			String title = excel.readStringCell(guestdatasheet, 1, 1);
			String firstname = excel.readStringCell(guestdatasheet, 1, 2);
			String lastname = excel.readStringCell(guestdatasheet, 1, 3);
			String Email = excel.readStringCell(guestdatasheet, 1, 4);
			String phoneNo = excel.readStringCell(guestdatasheet, 1, 5);
	
		ho.fill_guest_details(title, firstname, lastname, Email, phoneNo);
		
		 String creditcardNo = excel.readStringCell(paymentdatasheet, 1, 1);
		 String expMonth = excel.readStringCell(paymentdatasheet, 1, 2);
		 String expYear = excel.readStringCell(paymentdatasheet, 1, 3);
		 String Cvv = excel.readStringCell(paymentdatasheet, 1, 4);
		 String Name = excel.readStringCell(paymentdatasheet, 1, 5);
		 
		 py.fillin_payment_details(creditcardNo, expMonth, expYear, Cvv, Name);
		   
	}

}
