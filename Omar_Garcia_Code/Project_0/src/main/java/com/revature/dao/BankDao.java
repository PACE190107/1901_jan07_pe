package com.revature.dao;

import com.revature.Exceptions.UsernameAlreadyExistException;

public interface BankDao {
	
	public void deposite(int amount);
	public void withdraw(int amount);
	public boolean login(String username, String password);
	public boolean register(String username, String password);
	public void checkUsername(String username) throws UsernameAlreadyExistException;

}
