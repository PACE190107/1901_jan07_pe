package com.revature.Exceptions;

public class EmptyAccountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyAccountException() {
		System.out.println("Could not find any valid accounts");
	}
}
