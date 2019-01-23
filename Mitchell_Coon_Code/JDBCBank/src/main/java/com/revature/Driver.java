package com.revature;

public class Driver {

	public static void main(String[] args) {
		
		// First present the login screen;
		// ask the user to enter their username and password, or register.
		
		new UserConsole().LoginScreen();
		
		System.out.println("Goodbye!");
		
		// After logging in, ask if the user wants to deposit, withdraw, logout

	}

}