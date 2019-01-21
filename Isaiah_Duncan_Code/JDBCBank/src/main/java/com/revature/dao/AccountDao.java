package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface AccountDao {

	//public boolean addNewChecking();
	public List<Account> displayUserAccountsImpl(User user);
	public boolean withdrawFromAccountImpl(Account acct);
	public boolean depositFromAccountImpl(Account acct);
	public boolean addCheckingAcctImpl(Account acct);
	public boolean addSavingsAcctImpl(Account acct);
	public boolean removeAccountImpl(Account acct);
	
	
}
