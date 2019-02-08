package com.revature.tjordan.base;

/**
 * @author Timothy Jordan
 * @Purpose Custom Exeception for invalid login.
 *
 */
public class InvalidLoginException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public InvalidLoginException(String message) {
		super(message);
	}
	

}
