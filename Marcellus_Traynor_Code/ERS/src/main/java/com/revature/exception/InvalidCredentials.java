package com.revature.exception;

public class InvalidCredentials extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3131106177758887732L;

	public String getMessage()
	{
		return "\nINCORRECT CREDENTIALS: YOUR USERNAME AND/OR PASSWORD ARE INCORRECT. TRY AGAIN.\n";
	}
}