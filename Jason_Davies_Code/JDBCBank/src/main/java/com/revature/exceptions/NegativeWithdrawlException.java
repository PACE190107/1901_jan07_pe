package com.revature.exceptions;

public class NegativeWithdrawlException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "You must withdraw a value greater than or equal to 0.";
	}

}
