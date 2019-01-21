package com.revature.exceptions;

public class InvalidCreds extends RuntimeException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -651441318169737234L;
	
	public String getMessage()
	{
		return "\nINCORRECT CREDENTIALS: YOUR USERNAME AND/OR PASSWORD ARE INCORRECT. TRY AGAIN.\n";
	}
}