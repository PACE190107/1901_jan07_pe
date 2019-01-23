package com.revature.exceptions;

public class NegativeDepositException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 867837181847689974L;

	public String getMessage() {
		return "Please enter a positive deposit amount.";
	}
}
