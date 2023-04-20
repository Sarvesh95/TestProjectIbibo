package pompages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import frameworkUtility.FuncLibrary;

public class Ibibo_Searchresult extends FuncLibrary{
	WebDriver driver;
	
	@FindBy(xpath ="//h4[@itemprop='name']")
	List<WebElement> List_of_hotels;
	
	@FindBy(xpath ="//h4[text()='Green Apple Residence']")
	WebElement hotel_name;
	
	@FindBy(xpath = "//div[@class='infinite-scroll-component ']/div/div")
	WebElement first_hotel;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/section[1]/div/div/div[4]/div[3]")
	WebElement price_range;
	
	@FindBy(xpath = "//span[@class='Filtersstyles__AverageReviewText-sc-bkjigy-9 hqHLna' and text()='4']")
	WebElement ratings;
	
	@FindBy(xpath = "//span[@itemprop='ratingValue']")
	List<WebElement>  ratings_list;
	
	@FindBy(xpath = "//p[@itemprop='priceRange']")
	List<WebElement> PriceRange_list;
	
	
	public Ibibo_Searchresult(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	int hotelCount;
	public String[] Hotels_found() {
		
		hotelCount = List_of_hotels.size();
		
		String[] arrhotels = new String[hotelCount];
		
		Reporter.log("the total no of hotels found are "+hotelCount);
		
		Reporter.log("here are the list of hotels found");
		
		for(int i=0; i<hotelCount; i++) {
			
		    arrhotels[i] = List_of_hotels.get(i).getText();
			String hotels = List_of_hotels.get(i).getText();
			
			Reporter.log(hotels);
		}
		
		return arrhotels;
		
		
	}
	
	public String verify_Hotelname() {
		hotelCount = List_of_hotels.size();
		System.out.println("so total hotecount is "+hotelCount);
		String hotelname = getText(hotel_name);
		
		System.out.println("the hotelname identified is "+hotelname);
		String[] arrHotels =Hotels_found();

        for(int i =0; i<hotelCount; i++) {
        	
        	if(hotelname.equalsIgnoreCase(arrHotels[i])) {
        		
        		Assert.assertTrue(hotelname.equalsIgnoreCase(arrHotels[i]));
        		
        		
        		JavascriptExecutor exe = (JavascriptExecutor)driver;
        		exe.executeScript("arguments[0].click();", hotel_name);
        		
        		break;
        		
        	}
        	
        }
        
        return hotelname;
		
	}
	
	public String verify_first_Hotelname() {
		
		hotelCount = List_of_hotels.size();
		
		String[] arrHotels =Hotels_found();
        String  hotelname  = arrHotels[0];
  
		System.out.println("the hotelname identified is "+hotelname);
		        for(int i =0; i<hotelCount; i++) {
        	
        	if(hotelname.equalsIgnoreCase(arrHotels[i])) {
        		
        		Assert.assertTrue(hotelname.equalsIgnoreCase(arrHotels[i]));
        		
        		WebElement ele = driver.findElement(By.xpath("//h4[text()='"+hotelname+"']"));
        		
        		JavascriptExecutor exe = (JavascriptExecutor)driver;
        		exe.executeScript("arguments[0].click();", ele);
        		
        		break;
        		
        	}
        	
        }
        
        return hotelname;
		
	}
	
	public void selectRatings_checkbox() {
		
		if(!ratings.isSelected()) {
			eleClick(ratings);
		}else {
			System.out.println("checkbox is already selected");
		}
	}
	
	public void selectPricerange_checkbox() {
		
		if(!price_range.isSelected()) {
			
			eleClick(price_range);
		}else {
			System.out.println("checkbox is already selected");
		}
	}
	
	public void verifyPriceRange_filter() {
	 int size = PriceRange_list.size();
	 System.out.println("here is the list of prices received : ");
	 
	 for(int i=0 ; i<size; i++) {
		 
		String priceText = PriceRange_list.get(i).getText();
		 int priceInt = Integer.parseInt(priceText);
			 System.out.println(priceInt);
			 Assert.assertTrue("price filter is not applied correctly",priceInt>2000 && priceInt<4001);	 
	 }
	}
	
	public void verifyRatings_filter() {
		
		int size = ratings_list.size();
		
		System.out.println("here is the list of ratings received : ");

		for(int i = 0; i<size; i++) {
			
			String ratingText = ratings_list.get(i).getAttribute("content");
			double ratingDouble = Double.parseDouble(ratingText);
			
			System.out.println(ratingDouble);
			 Assert.assertTrue("rating filter is not applied correctly", ratingDouble>3.9);
			 
			}
	}
	public void apply_and_verifyFilters() {
		
		selectPricerange_checkbox();
		sleep(2000);
		selectRatings_checkbox();
		sleep(2000);
		verifyPriceRange_filter();
		
		verifyRatings_filter();
		
		
		
	}
	
}
