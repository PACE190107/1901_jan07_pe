package com.revature.exceptions;

public class IncorrectPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 924325730650606955L;

	public String getMessage() {
		return "Incorrect Password. Please try again.";
	}
}