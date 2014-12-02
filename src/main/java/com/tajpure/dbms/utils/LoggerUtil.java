package com.tajpure.dbms.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {
	
	private static Logger logger = Logger.getLogger (LoggerUtil.class.getName ());
	
	static {
		PropertyConfigurator.configure ("src/main/resource/log4j.properties");
		/*logger.shutdown();*/
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
}
