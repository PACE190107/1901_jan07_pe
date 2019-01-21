package com.revature.Exceptions;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(){
		System.out.println("User could not be found");
	}
}
