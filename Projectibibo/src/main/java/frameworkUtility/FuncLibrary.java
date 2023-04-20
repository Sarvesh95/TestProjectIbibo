package frameworkUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FuncLibrary {

	public WebDriver driver ;
	WebDriverWait wait = null;
	Actions act = null;
	
	protected FuncUtilLibrary futil = new FuncUtilLibrary();
	public WebDriver startBrowser() {
		
		
		
		if(futil.readProperty("browser").equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
            option.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver(option);
			
			
		}else if(futil.readProperty("browser").equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			driver = new  FirefoxDriver();
			
		}else if(futil.readProperty("browser").equalsIgnoreCase("edge")) {
			
           WebDriverManager.edgedriver().setup();
			
			driver = new  EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver;
	
		
	}
	
	public void refresh() {
		
		driver.navigate().refresh();
	}
	
	
	public void forward() {
		
		driver.navigate().forward();
	}
	
	public void backword() {
		
		driver.navigate().back();
		
		//driver.findElement(By.className("name")).getAttribute("value");
	}
	
	
	public void launchURL() {
	
		driver.get(futil.readProperty("siteURL"));
	}
	
	public void enterText(WebElement ele, String data) {
		
		ele.sendKeys(data);
	}
	
	public void submition(WebElement ele) {
		
		ele.submit();
	}
	
	public void eleClick(WebElement ele) {
		
		ele.click();
	}
	
	public void sleep(long time) {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectByValue(WebElement ele, String val) {
		
		Select sel = new Select(ele);
		      sel.selectByValue(val);
	}

	public void selectByIndex(WebElement ele, int index) {
		
		Select sel = new Select(ele);
		       sel.selectByIndex(index);
	}
	
	public void selectByVisibleText(WebElement ele, String text) {
		
		Select sel = new Select(ele);
               sel.selectByVisibleText(text);
	}
	
	public void mouseHover(WebElement ele) {
		
		        act = new Actions(driver);
		        act.moveToElement(ele).perform();;
	}
	
	public void selectsubmenu(WebElement ele1, WebElement ele2) {
		
            act = new Actions(driver);
		
		    act.moveToElement(ele1).perform();
		    
		    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(ele2));
			act.moveToElement(ele2).click().perform();
		
		
	}
	
	public void copyAndpaste(WebElement ele, String text) {
		
        StringSelection sel = new StringSelection(text);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel, null);
		
		Robot rob;
		try {
			rob = new Robot();
			
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_V);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
		
		public void switchTonewWindow(WebDriver driver) throws InterruptedException {
			this.driver = driver;
			Thread.sleep(2000);
			String OriginalWindow = driver.getWindowHandle();
			
			for (String windowHandle : driver.getWindowHandles()) {
			    if(!OriginalWindow.contentEquals(windowHandle)) {
			        driver.switchTo().window(windowHandle);
			        break;
			    }
		  }
	}
		
		public void switch_to_Tab(WebDriver driver, int tabhandle) {
		
		
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			
			driver.switchTo().window(newTab.get(tabhandle));
		}
		
		
		public String  getText(WebElement ele) {
			String Text = ele.getText();
			
			return Text;
		}
		
		public String getAttribute(WebElement ele, String AttributeName) {
			
			 String Attribute = ele.getAttribute(AttributeName);
			 
			 return Attribute;
		}
		
		public void slider(WebElement ele, int offset1, int offset2) throws InterruptedException {
			
			
			act = new Actions(driver);
			act.dragAndDropBy(ele, offset1, offset2).perform();
			Thread.sleep(1000);
		}
		
		public void verifyText(WebElement ele, String expectedText) {
			
		String ActualText =	ele.getText();
		
		Assert.assertEquals(ActualText, expectedText,"the expected Output not matched with the actual output");
			
		}
		
		public void list_of_elements(List<WebElement> list) {
			
			for(int i = 0; i<list.size(); i++) {
				
				Reporter.log(list.get(i).getText());
				
				System.out.println(list.get(i).getText());
			}
		}
		
		public void download_Image(String srcLink, String imagename) throws IOException {
			String Imagename = imagename;
			URL imageURL = new URL(srcLink);
			
			   BufferedImage saveimage = ImageIO.read(imageURL);
			   
			   ImageIO.write(saveimage, "jpg", new File("C:/Users/sarve/eclipse-workspace/Projectibibo/src/test/resources/SavedImages/"+Imagename+".png"));
		}
		
		public void verify_Element(WebElement ele) {
			
		  Assert.assertTrue(ele.isDisplayed());
			
		}
}




