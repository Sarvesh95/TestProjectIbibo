package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import frameworkUtility.FuncLibrary;

public class Ibibo_Payment extends FuncLibrary {

	WebDriver driver;
	
	@FindBy(xpath = "//ul[@class='paymentListCtr']/li[2]")
	WebElement select_payment;
	
	@FindBy(id = "cardNumber")
	WebElement cardnumber;
	
	@FindBy(id = "expiryMonth")
	WebElement month;
	
	@FindBy(id = "expiryYear")
	WebElement year;
	
	@FindBy(id = "cardCvv")
	WebElement Cvv;
	
	@FindBy(id = "nameOnCard")
	WebElement name;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit_payment;
	
	@FindBy(xpath = "//div[@class='reason appendBottom20 font14 bold']")
	WebElement error_message;
	
	@FindBy(xpath = "//button[@type='submit']" )
	WebElement ok_submit;
	
	public Ibibo_Payment(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void selectPayment() {
		
		eleClick(select_payment);
	}
	
	public void enterCardNo(String number) {
		
		enterText(cardnumber,  number);
	}
	
	public void enterMonth(String Month) {
		
		enterText(month, Month);
	}
	
	public void enterYear(String Year) {
		
		enterText(year, Year);
	}
	
	public void enterCvv(String cvv) {
		
		enterText(Cvv, cvv);
	}
	
	
	public void enterName(String Name) {
		
		enterText(name, Name);
	}
	
	public void click_on_pay() {
		
		eleClick(submit_payment);
	}
	
	public void observe_error_message() {
		
		Reporter.log(getText(error_message));
		
		System.out.println(getText(error_message));
	}
	
	public void proceed() {
		
		eleClick(ok_submit);
	}
	
	public void fillin_payment_details(String number, String Month, String Year, String cvv, String Name) {
		
		selectPayment();
		
		enterCardNo(number);
		
		enterMonth(Month);
		
		enterYear(Year);
		
		enterCvv(cvv);
		
		enterName(Name);
		
		click_on_pay();
		
		observe_error_message();
		
		proceed();
		
	}
	
}
