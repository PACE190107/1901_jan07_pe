package com.revature.logging;

import org.apache.log4j.Logger;

public class LoggingExample {
	
	final static Logger x = Logger.getLogger(LoggingExample.class);

	public static void main(String[] args) {
		
		x.info("this is how informationn gets printed");
		x.warn("warning messages");
		x.error("error messages");
		x.debug("error messages");
	}

}
