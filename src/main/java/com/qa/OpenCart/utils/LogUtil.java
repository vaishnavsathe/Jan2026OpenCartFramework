package com.qa.OpenCart.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.OpenCart.factory.DriverFactory;

public class LogUtil {

	public static Logger log = LogManager.getLogger(DriverFactory.class);
	
	public static void info(String msg) {
		log.info(msg);
	}
	
	public static void warn(String msg) {
		log.warn(msg);
	}
	
	public static void error(String msg) {
		log.error(msg);
	}
}