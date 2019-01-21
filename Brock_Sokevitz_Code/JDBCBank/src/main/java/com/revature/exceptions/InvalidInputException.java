package com.revature.exceptions;

public class InvalidInputException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1609488154697326613L;

	public String getMessage() {
		return "Invalid input.";
	}
}
