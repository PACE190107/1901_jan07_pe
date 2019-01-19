package com.revature.dao;

import java.sql.SQLException;

import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.UsernameAlreadyExistException;

public interface BankDao {
	
	public void deposite(int amount, int account, int user) throws SQLException, DepositFailedException;
	public void withdraw(int amount, int account, int user);
	public void checkUsername(String username) throws UsernameAlreadyExistException;
	public void getAccounts(int userID) throws SQLException;

}
