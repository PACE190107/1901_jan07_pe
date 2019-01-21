package com.revature.exceptions;

public class NotEnoughFundsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1681162315067309725L;

	public String getMessage() {
		return "Not enough funds for the withdraw.";
	}
}
