package com.revature.exceptions;

public class OverdrawException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public String  getMessage() {
		return "Your account doesn't have the funds for this request.";
	}
}
