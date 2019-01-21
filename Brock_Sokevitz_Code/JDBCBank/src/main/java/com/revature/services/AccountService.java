package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDaoImplementation;
import com.revature.models.Account;

public class AccountService {

private static AccountService accountService;
	
	private AccountService() {
		
	}
	
	
	public static AccountService getAccountService() {
		if(accountService == null)
			accountService = new AccountService();
		return accountService;
	}
	
	public boolean insertAccount(Account account) {
		return AccountDaoImplementation.getAccountDao().insertAccount(account);
		}
	
	public boolean deleteAccount(int accountID) {
		return AccountDaoImplementation.getAccountDao().deleteAccount(accountID);
		}
	
	public boolean updateBalance(int accountID, double amount) {
		return AccountDaoImplementation.getAccountDao().updateBalance(accountID, amount);
		}
	
	public Account getAccount(int accountID) {
		return AccountDaoImplementation.getAccountDao().getAccount(accountID);
		}
	
	public List<Account> getAllAccounts(int userID){
		return AccountDaoImplementation.getAccountDao().getAllAccounts(userID);
		}


	public boolean exists(int accountID, int userID) {
		// TODO Auto-generated method stub
		return AccountDaoImplementation.getAccountDao().accountExists(accountID, userID);
	}
	
	public double getBalance(int accountID) {
		return AccountDaoImplementation.getAccountDao().getBalance(accountID);
		}


	public boolean deleteAllAccounts(int userID) {
		// TODO Auto-generated method stub
		return AccountDaoImplementation.getAccountDao().deleteAllUsersAccounts(userID);
	}
	
}
