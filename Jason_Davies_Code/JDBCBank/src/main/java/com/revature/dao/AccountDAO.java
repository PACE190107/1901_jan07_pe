package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.Account;

public interface AccountDAO {
	public boolean insertAccount(Account account);
	public boolean updateAccount(Account account);
	public boolean deleteAccount(int accountId);
	public boolean deleteAllAccounts(int userId);
	public ArrayList<Account> getAllAccounts(int userId);
}
