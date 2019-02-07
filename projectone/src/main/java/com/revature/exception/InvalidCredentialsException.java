package com.revature.exception;

public class InvalidCredentialsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException() {
		super("Invalid credentials. No such employee found");
	}

}
