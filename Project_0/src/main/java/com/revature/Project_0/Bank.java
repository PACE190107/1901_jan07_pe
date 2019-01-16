package com.revature.Project_0;

import com.revature.Exceptions.UsernameAlreadyExistException;

public interface Bank {
	
	public void deposite(int amount);
	public void withdraw(int amount);
	public boolean login(String username, String password);
	public boolean register(String username, String password);
	public void checkUsername(String username) throws UsernameAlreadyExistException;

}
