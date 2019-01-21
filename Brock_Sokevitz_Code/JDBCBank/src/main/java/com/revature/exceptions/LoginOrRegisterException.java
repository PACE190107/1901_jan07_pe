package com.revature.exceptions;

public class LoginOrRegisterException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6531859898309200841L;

	public String getMessage() {
		return "Invalid input. Please login, register, or exit.";
	}
}
