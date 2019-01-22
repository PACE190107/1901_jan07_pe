package com.revature.services;

import java.util.ArrayList;

import com.revature.dao.ATMQuery;
import com.revature.exceptions.LoginFailedException;
import com.revature.exceptions.OutstandingBalanceException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.models.User;

public class ATMService {
	
	private static ATMService atmService;
	
	private ATMService() {
		super();
	}
	
	public static ATMService getATMService() {
		if(atmService == null) {
			atmService = new ATMService();
			return atmService;
		} else return atmService;
	}
	public User verifyLogin(User u) {
		try {return ATMQuery.getATMQuery().verifyLogin(u);}
		catch (LoginFailedException e) {
			System.out.println("\nLogin Failed, try again or register a new account.");
			return null;
		}
	}
	public boolean registerNewUser(User u) {
		return ATMQuery.getATMQuery().registerNewUser(u);
	}
	
	public ArrayList<User> listAllUsers() {
		return ATMQuery.getATMQuery().listAllUsers();
	}
	
	public ArrayList<Account> getUserAccounts(User u) {
		return ATMQuery.getATMQuery().getUserAccounts(u);
	}
	
	public boolean withdraw(Account account, double withdraw) throws OverdraftException {
		return ATMQuery.getATMQuery().withdraw(account, withdraw);
	}

	public boolean deposit(Account account, double deposit) {
		return ATMQuery.getATMQuery().deposit(account, deposit);
	}

	public boolean createAccountRequest(String account, User user) {
		return ATMQuery.getATMQuery().createAccountRequest(account, user);
	}

	public boolean deleteAccountRequest(Account account) throws OutstandingBalanceException {
		return ATMQuery.getATMQuery().deleteAccountRequest(account);
	}
	
	public boolean deleteAllUsers() {
		return ATMQuery.getATMQuery().deleteAllUsers();
	}
	
	public boolean deleteUser(User u) {
		return ATMQuery.getATMQuery().deleteUser(u);
	}
	
	public boolean udpateUser(User u) {
		return ATMQuery.getATMQuery().updateUser(u);
	}
	
	public boolean createUser(User u) {
		return ATMQuery.getATMQuery().createUser(u);
	}
}
