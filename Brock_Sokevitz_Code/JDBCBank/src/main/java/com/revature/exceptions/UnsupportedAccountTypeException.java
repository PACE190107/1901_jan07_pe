package com.revature.exceptions;

public class UnsupportedAccountTypeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1104801305149045534L;

	public String getMessage() {
		return "Unsupported Account Type.";
	}
}
