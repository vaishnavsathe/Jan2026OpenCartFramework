package com.qa.OpenCart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	//create method to accept the values from config.properties - Do you have access to config propoerties??
	//to call the method - I need object..
	
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {//in string: true, convert to boolean
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {//in string: true, convert to boolean
			co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {//in string: true, convert to boolean
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {//in string: true, convert to boolean
			fo.addArguments("--incognito");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {//in string: true, convert to boolean
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {//in string: true, convert to boolean
			eo.addArguments("--InPrivate");
		}
		return eo;
	}
	
	
}