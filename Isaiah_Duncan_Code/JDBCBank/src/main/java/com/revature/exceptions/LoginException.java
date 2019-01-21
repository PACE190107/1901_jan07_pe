package com.revature.exceptions;

import org.apache.log4j.Logger;

import com.revature.utils.JDBCConnectUtil;

public class LoginException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1398508709896347752L;
	
	final static Logger Log = Logger.getLogger(JDBCConnectUtil.class);
	
	public LoginException (String message) {
		super(message);
		Log.info(message);
	}

}
