package com.revature.exceptions;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class UsernameExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	
	public String getMessage() {
		String message = "That username already exists.";
		Log.info(message);
		return message;
	}
	
}
