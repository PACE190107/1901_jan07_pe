package com.revature.Project_0;

import com.revature.Exceptions.*;

public class MyBank implements Bank{

	public void deposite(int amount) {
		System.out.println("Deposit");
	}

	public void withdraw(int amount) {
		System.out.println("Withdraw");
		
	}

	public boolean login(String username, String password) {
		System.out.println("User : " + username + "\npassword : " + password);
		return true;
	}

	public boolean register(String username, String password) {
		System.out.println("User : " + username + "\npassword : " + password);
		return true;
	}

	public void checkUsername(String username) throws UsernameAlreadyExistException {
		if(username.equals("Username")) {
			throw new UsernameAlreadyExistException();
		}
	}
	

}
