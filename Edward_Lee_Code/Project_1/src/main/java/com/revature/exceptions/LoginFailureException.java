package com.revature.exceptions;

public class LoginFailureException extends Exception{
	public LoginFailureException() {
		super("Username or password were incorrect");
	}
}
