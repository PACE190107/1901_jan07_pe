package com.revature.dao;

import java.util.ArrayList;

import com.revature.exceptions.LoginFailedException;
import com.revature.exceptions.OutstandingBalanceException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;

public interface ATMQueryInterface {
	public boolean registerNewUser(User u);
	public User verifyLogin(User u) throws LoginFailedException;
	public boolean withdraw(Account account, double withdraw) throws OverdraftException;
	public boolean deposit(Account account, double deposit);
	public boolean createAccountRequest(String account, User user);
	public boolean deleteAccountRequest(Account account) throws OutstandingBalanceException;
	public ArrayList<Account> getUserAccounts(User u);
	public boolean createUser(User u);
	public boolean updateUser(User u);
	public ArrayList<User> listAllUsers();
	public boolean deleteUser(User u);
	public boolean deleteAllUsers();
}
