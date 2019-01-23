package com.revature.services;

import java.util.ArrayList;

import com.revature.dao.AccountDAOImplementation;
import com.revature.models.Account;

public class AccountService {
	
	private static AccountService instance;
	
	private AccountService() {	}
	public static AccountService getAccountService() {
		if (instance == null) {
			instance = new AccountService();
		}
		return instance;
	}
	
	public boolean insertAccount(Account account) {
		return AccountDAOImplementation.getAccountDAO().insertAccount(account);
	}
	
	public boolean updateAccount(Account account) {
		return AccountDAOImplementation.getAccountDAO().updateAccount(account);
	}
	
	public boolean deleteAccount(int accountId) {
		return AccountDAOImplementation.getAccountDAO().deleteAccount(accountId);
	}
	
	public boolean deleteAllAccount(int userId) {
		return AccountDAOImplementation.getAccountDAO().deleteAllAccounts(userId);
	}
	
	public ArrayList<Account> getAllAccounts(int userId) {
		return AccountDAOImplementation.getAccountDAO().getAllAccounts(userId);
	}
	
}
