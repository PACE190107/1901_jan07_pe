package com.revature.tjordan.Exceptions;

public class NonExistingUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NonExistingUserException(String message) {
		super(message);
	}

}
