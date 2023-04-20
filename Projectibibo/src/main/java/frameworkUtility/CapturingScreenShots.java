package frameworkUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CapturingScreenShots {
	
	WebDriver driver;
	
	public CapturingScreenShots(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	public void ScreenCapture() {
		
	File srcFile =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	File destFile = new File("C:\\Users\\sarve\\eclipse-workspace\\Projectibibo\\src\\test\\resources\\Screenshots"+driver.getTitle()+".png");
	
	             try {
					FileUtils.copyFile(srcFile, destFile);
					
					Reporter.log("<br><img src='"+destFile+"' height = '400' width ='700' /></br>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
