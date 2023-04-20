package testscripts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{

	int retryCount = 0;
	
	int maxretrycount = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(retryCount<maxretrycount) {
			
			retryCount++;
			
			return true;
		}
		return false;
	}

}
