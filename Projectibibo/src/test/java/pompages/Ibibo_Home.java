package pompages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkUtility.FuncLibrary;

public class Ibibo_Home extends FuncLibrary{

	WebDriver driver;
	
	@FindBy(xpath = "//span[@class='logSprite icClose']")
	WebElement close_modal;
	
	@FindBy(linkText = "Hotels")
	WebElement tab_hotels;
	
	@FindBy(xpath = "//input[@data-testid='home-autosuggest-input']")
	WebElement enter_location;
	
	@FindBy(xpath = "//div[@data-testid='openCheckinCalendar']")
	WebElement open_calender;
	
	@FindBy(xpath = "//li/span[@data-testid='date_27_2_2023']")
	WebElement march_27;
	
	@FindBy(xpath = "//li/span[@data-testid='date_29_2_2023']")
	WebElement march_29;
	
	@FindBy(xpath = "//div/input[@type='text']")
	WebElement select_Guestandroom;
	
	@FindBy(xpath = "//div/span[@data-testid='button-adult-dec']")
	WebElement decr_adultTo1;
	
	@FindBy(xpath = "//button[text()='Done']")
	WebElement Done;
	
	@FindBy(xpath = "//button[text()='Search']")
	WebElement Search;
	
	public Ibibo_Home(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	public void close_the_popup() {
		eleClick(close_modal);
	}
	public void click_on_hotelsTab() {
		
		eleClick(tab_hotels);
	}
	
	public void enter_location(String location) throws AWTException {
		
		enterText(enter_location, location);
		sleep(3000);
		Robot rob = new Robot();
		
		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);
		rob.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void select_checkinAndchekout() {
		
		eleClick(open_calender);
		sleep(2000);
		eleClick(march_27);
		sleep(2000);
		eleClick(march_29);
		
	
	}
	
	public void select_adultsandrooms() {
		
		eleClick(select_Guestandroom);
		sleep(2000);
		eleClick(decr_adultTo1);
		
		sleep(1000);
		eleClick(Done);
	}
	
	public void click_on_search() {
		
		eleClick(Search);
	}
	
	
	public void searchHotels(String location) {
		
		close_the_popup();
		
		click_on_hotelsTab();
		
		try {
			enter_location(location);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		select_checkinAndchekout();
		
		select_adultsandrooms();
		
		click_on_search();
		
		
		
	}
	
	
	
	
}
