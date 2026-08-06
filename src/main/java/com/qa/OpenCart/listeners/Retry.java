package com.qa.OpenCart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	private int count =0;
	private static int maxTry =2;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(!result.isSuccess()) {
			if(count<maxTry) {
				count++;   //1
				result.setStatus(result.FAILURE);
				return true;   //Tells testNG to re-run the test
			}
			else {
				result.setStatus(result.FAILURE);
			}
		}
		else {
			result.setStatus(result.SUCCESS);
		}
		
		return false;
	}

}