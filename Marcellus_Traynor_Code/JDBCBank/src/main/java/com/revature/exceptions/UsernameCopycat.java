package com.revature.exceptions;

public class UsernameCopycat extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7081031414542311694L;
	
	public String getMessage()
	{
		return "\nTHE USERNAME YOU HAVE ENTERED IS ALREADY TAKEN. CHOOSE A DIFFERENT USERNAME AND TRY AGAIN.\n";
	}
}