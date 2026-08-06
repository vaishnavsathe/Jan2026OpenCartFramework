package com.qa.OpenCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.OpenCart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager opsMgr;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Logger log = LogManager.getLogger(DriverFactory.class);
	//3 activities can be done with this instance: info / error / warning
	
	public static String highlight;

	/**
	 * This method is used to initialize the driver on the basis of browser name passed
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		log.info("Properties: "+prop);
		String browserName = prop.getProperty("browser");	
//		System.out.println("Browser name: "+browserName);
		log.info("Browser name: "+browserName);
		
		opsMgr = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight");
		
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(opsMgr.getChromeOptions()));
//			driver = new ChromeDriver(opsMgr.getChromeOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(opsMgr.getEdgeOptions()));
//			driver = new EdgeDriver(opsMgr.getEdgeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(opsMgr.getFirefoxOptions()));
//			driver = new FirefoxDriver(opsMgr.getFirefoxOptions());
			break;
		default:
//			System.out.println("Please pass the valid browser name..."+browserName);
			log.error("Please pass the valid browser name..."+browserName);
			throw new BrowserException("== INVALID BROWSER ==");
		}
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		
		return getDriver();
	}
	
	/**
	 * getDriver: get the local thread copy of the driver
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	} 
	

	/**
	 * This method is used to initialize the properties from config.properties file
	 * @return
	 */
	
	public Properties initProp() {
		prop = new Properties();
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//mvn clean install -Denv="uat"
		
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		
		try {
			if (envName == null) {
//				System.out.println("Env is null, hence running the tests on QA env by default..");
				log.warn("Env is null, hence running the tests on QA env by default..");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}
			else {
//				System.out.println("Running tests on env: "+envName);
				log.info("Running tests on env: "+envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					log.error("---Invalid env name----"+envName);
					throw new BrowserException("---Invalid env name----: "+envName);
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	//Method to capture screenshot..	
	public static File getScreenshotFile() {
		
		File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return file;
	}
	
}