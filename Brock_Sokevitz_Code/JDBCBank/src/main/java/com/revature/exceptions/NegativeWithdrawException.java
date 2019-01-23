package com.revature.exceptions;

public class NegativeWithdrawException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6510650592459909284L;

	
	public String getMessage() {
		return "Please enter a withdraw amount greater than 0.";
	}
}
