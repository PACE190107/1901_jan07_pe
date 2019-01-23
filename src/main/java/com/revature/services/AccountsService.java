package com.revature.services;

import java.util.List;

import com.revature.dao.AccountsDaoImp;
import com.revature.models.Accounts;

public class AccountsService {

	private static AccountsService accountsService;
	private AccountsService() {
	}
	
	public static AccountsService getAccountsService() {
		if (accountsService == null) {
			accountsService = new AccountsService();
		}
		return accountsService;
	}
	
	public List<Accounts> getAllAccountsDetails() {
		return AccountsDaoImp.getAccountsDao().getAllAccounts();
	}
	
	public boolean registerAccounts(Accounts accounts) {
		return AccountsDaoImp.getAccountsDao().insertAccounts(accounts);
	}
	
	public boolean registerAccountsProcedure(Accounts accounts) {
		return AccountsDaoImp.getAccountsDao().insertAccountsProcedure(accounts);
	}
	
	public Accounts getAccounts() {
		return AccountsDaoImp.getAccountsDao().getAccounts();
	}
	
	public boolean makeDepositProcedure(Accounts accounts) {
		return AccountsDaoImp.getAccountsDao().updateAccountsProcedure(accounts);
	}
	
	public boolean removeAccountsProcedure(Accounts accounts) {
		return AccountsDaoImp.getAccountsDao().deleteAccountsProcedure(accounts);
	}
}
