package com.qa.OpenCart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.OpenCart.factory.DriverFactory;
import com.qa.OpenCart.pages.AccountsPage;
import com.qa.OpenCart.pages.LoginPage;
import com.qa.OpenCart.pages.ProductInfoPage;
import com.qa.OpenCart.pages.RegisterPage;
import com.qa.OpenCart.pages.SearchResultsPage;
import com.qa.OpenCart.utils.LogUtil;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage acctPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage; 
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		//browser name is passed from .xml file
		if(browserName !=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);//threadlocal driver
		loginPage = new LoginPage(driver);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext result) {
		LogUtil.info("------Staring the test case-----"+result.getName());
	}
	
	
	//aftermethod --> This will execute after @Test
	//call takeScreenshot method Driverfactory.Scrrenshot()
	
	@AfterMethod //This will be running after each @Test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			//take screenshot
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		LogUtil.info("------Ending the test case-----"+result.getName());
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}