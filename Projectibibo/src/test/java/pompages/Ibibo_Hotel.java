package pompages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import frameworkUtility.FuncLibrary;
import frameworkUtility.FuncUtilLibrary;

public class Ibibo_Hotel extends FuncLibrary{

	WebDriver driver;
	
	WebDriverWait wait ;
	
	@FindBy(xpath = "//h1[text()= 'Green Apple Residence']")
	WebElement hotel_title;
	
	@FindBy(xpath = "//div[@id='2']/div/div[3]/div/div[2]/div[2]/button")
	WebElement standard_room;
	
	@FindBy(xpath = "//p[text()='Mon, 27 Mar 2023']")
	WebElement verify_checkin;
	
	@FindBy(xpath = "//p[text()='Wed, 29 Mar 2023']")
	WebElement verify_checkout;
	
	@FindBy(xpath = "//p[text()='1 Guest | 1 Room']")
	WebElement verify_GuestRoom;
	
	@FindBy(xpath = "//select[@class='PersonalProfile__NameEnterSelect-sc-1r9ak8b-7 hkMeMW']")
	WebElement title;
	
	@FindBy(xpath = "//input[@placeholder='Enter First Name']")
	WebElement firstname;
	
	@FindBy(xpath = "//input[@placeholder='Enter Last Name']")
	WebElement lastname;
	
	@FindBy(xpath ="//input[@placeholder='Enter Email Address']")
	WebElement emailid;
	
	@FindBy(xpath ="//input[@placeholder='Enter Phone Number']")
	WebElement phoneNo;
	
	@FindBy(xpath ="//button/span[text()='Proceed To Payment Options']")
	WebElement proceed_to_pay;
	
	@FindBy(xpath ="//div[@class='Layouts__Column-sc-1yzlivq-3 Roomstyles__Column-sc-1ivl7fs-0 Roomstyles__RoomImageWrapper-sc-1ivl7fs-5 dSpsBx fuxubH fRJvPo']/img")
	List <WebElement> image_locations;
	
	@FindBy(xpath = "//*[@id=\"45000942108\"]/div/div[2]/div/a/div/img")
	WebElement image_location;
	
    @FindBy(xpath = "//section[@id='details-navigation']/div/ul/li[5]")
    WebElement location;
    
    @FindBy(xpath = "//input[@data-testid='d-LocationMap-input']")
    WebElement search_location;
    
    @FindBy(xpath = "//section[@id='details-navigation']/div/ul/li[3]/div/a")
    WebElement guest_review;
    
    @FindBy(xpath = "//div[@class='Layouts__Column-sc-1yzlivq-3 GuestReview__GoRatingBlock-sc-1twl4uk-0 dSpsBx brlAjs']")
    WebElement review_rating;
	
	@FindBy(xpath="//section[@id='details-navigation']/div/ul/li[7]/div/a")
    WebElement QnA;
    
    @FindBy(xpath = "//input[@placeholder='Have a Question? Search for Answers']")
    WebElement searchQnA;
    
    @FindBy(xpath = "//section[@id='details-navigation']/div/ul/li[4]/div/a")
    WebElement property_policies;
    
    @FindBy(xpath = "//h2[text()='Property Policies']")
    WebElement proprtyPolicy_title;
	
	public Ibibo_Hotel(WebDriver driver) {
		
		this.driver = driver;
	
		PageFactory.initElements(driver, this);
	}
	

	public void verify_booking_details() {
		
		String checkinSite =getText(verify_checkin);
		System.out.println(checkinSite);
	
		String checkoutSite = getText(verify_checkout);
		System.out.println(checkoutSite);
		
		String guestandroomSite = getText(verify_GuestRoom);
		System.out.println(guestandroomSite);
	
		Assert.assertTrue(guestandroomSite.contains(futil.readProperty("GuestAndRoom")) && checkinSite.contains(futil.readProperty("checkin"))
				&& checkoutSite.contains(futil.readProperty("checkout")), 
	    		"booking details not matched with expected details");
		
	}
	
	public void verify_hotel_title(String hotelname, String hotelTitle) {
		
		Assert.assertTrue(hotelname.contains(hotelTitle), "the hotelname not matches with the booked hotel");
		
	}
	
	public void select_standard_room() {
		
		eleClick(standard_room);
	}
	
	public void selectTitle(String Title) {
		
		selectByVisibleText(title, Title);
	}
	
	public void enterFirstname(String Firstname) {
		
		enterText(firstname, Firstname);
	}
	
	public void enterLastname(String Lastname) {
		
		enterText(lastname, Lastname);
	}
	
	public void enterEmail(String Email) {
		
		enterText(emailid, Email);
	}
	
	public void enterPhoneNo(String PhoneNo) {
		
		enterText(phoneNo, PhoneNo);
	}
	
	public void click_on_proceedTopayment() {
		eleClick(proceed_to_pay);
	}
	
	public void book_and_verify_Hotel(String hotelname, String hotelTitle) {
		
	     switch_to_Tab(driver, 1);
		verify_hotel_title(hotelname, hotelTitle);
		sleep(2000);
		select_standard_room();
		sleep(2000);
		verify_booking_details();
			
	}
	
	public void fill_guest_details(String title, String firstname, String lastname, String emailid, String phoneNo) {
		
		selectTitle(title);
		sleep(1000);
		enterFirstname(firstname);
		sleep(1000);
		enterLastname(lastname);
		sleep(1000);
		enterEmail(emailid);
		sleep(1000);
		enterPhoneNo(phoneNo);
		sleep(1000);
		click_on_proceedTopayment();
	}
	
	public void verify_locationTab() {
		
		/*JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", location);*/
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(location)).click();
		sleep(1000);
		verify_Element(search_location);
		
	}
	
	public void verify_guestReviewTab() {
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", guest_review);
		sleep(1000);
		verify_Element(review_rating);
	}
	
	public void verify_QnATab() {
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", QnA);
		sleep(1000);
		verify_Element(searchQnA);
	}
	
	public void verify_property_policyTab() {
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", property_policies);
		sleep(1000);
		verify_Element(proprtyPolicy_title);
	}
	public void get_hotel_details(String Imagename) throws IOException {
		   switch_to_Tab(driver, 1);
		  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		  wait.until(ExpectedConditions.
				  visibilityOfElementLocated
				  (By.xpath
				  ("//div[@class='Layouts__Column-sc-1yzlivq-3 Roomstyles__Column-sc-1ivl7fs-0 Roomstyles__RoomImageWrapper-sc-1ivl7fs-5 dSpsBx fuxubH fRJvPo']/img")));
		  int sizecount = image_locations.size();
		  
		  System.out.println("the total rooms available are "+sizecount);
		 
		  String[] arrayHotels = new String[sizecount];
		  
		  for(int i = 0; i<image_locations.size(); i++) {
			  
			   arrayHotels[i] =  image_locations.get(i).getAttribute("src");
			   
			   System.out.println("the hotel is "+arrayHotels[i]);
		  }
		  
		  String image = driver.findElement(By.xpath("//img[@alt='goStops OOTY Room Type - Bed in 12 Bed Mixed Non-AC Dormitory Room with Ensuite Bathroom']")).getAttribute("src");
		  
		  System.out.println("the image source found is "+image);
		 
		  sleep(1000);
		 
		
		
			 String first_image_src = arrayHotels[0];
			 download_Image(first_image_src, Imagename);
		
		
		
	
		
		Reporter.log("<br><img src='"+futil.readProperty("ImageFileLocation")+"/"+Imagename+".png' height='500' width='350'/>");
		
		Reporter.log("<br> Hotel room image");
		
		verify_locationTab();
		
		verify_guestReviewTab();
		
		verify_QnATab();
		
		verify_property_policyTab();
		
		
		
		
		
	}
}
