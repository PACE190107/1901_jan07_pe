package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.Exceptions.AccountNotFoundException;
import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.EmptyAccountException;
import com.revature.Exceptions.OverDraftException;
import com.revature.Exceptions.UsernameAlreadyExistException;
import com.revature.Exceptions.WithdrawException;

public interface BankDao {
	
	public void deposite(int amount, int account, int user) throws SQLException, DepositFailedException;
	public void withdraw(int amount, int account, int user) throws SQLException, OverDraftException, AccountNotFoundException, WithdrawException;
	public void checkUsername(String username) throws UsernameAlreadyExistException;
	public List<Integer> getAccounts(int userID, int command) throws SQLException, EmptyAccountException;

}
