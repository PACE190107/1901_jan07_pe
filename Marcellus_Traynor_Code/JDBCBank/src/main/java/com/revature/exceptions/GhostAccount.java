package com.revature.exceptions;

public class GhostAccount extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5126576204239540046L;

	public String getMessage()
	{
		return "\nTHE ACCOUNT YOU HAVE SELECTED DOESN'T EXIST. REVIEW THE ACCOUNTS AND TRY AGAIN.";
	}
}