package com.revature.logging;

import org.apache.log4j.Logger;

public class LoggingExample {
	
	//use to create log object
	//use log.info() instead of sysout() to print to the console
	final static Logger log = Logger.getLogger(LoggingExample.class);
	
	public static void main(String[] args) {
		log.info("this is how information gets printed");
		log.warn("warning messages");
		log.error("error messages");
		log.debug("error messages");
	}
}
