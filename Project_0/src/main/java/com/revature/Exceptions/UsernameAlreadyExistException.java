package com.revature.Exceptions;

public class UsernameAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameAlreadyExistException() {
		System.out.println("This username already exists");
	}

}
