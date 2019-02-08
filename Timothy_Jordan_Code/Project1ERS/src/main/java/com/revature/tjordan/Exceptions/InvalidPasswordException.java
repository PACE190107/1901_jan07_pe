package com.revature.tjordan.Exceptions;

public class InvalidPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	
	public InvalidPasswordException(String message) {
		super(message);
	}
}
