package com.revature.Exceptions;

public class EmptyUsersException extends Exception{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public EmptyUsersException() {
			System.out.println("No Users Where Found");
		}
}
