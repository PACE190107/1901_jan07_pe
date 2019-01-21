package com.revature.dao;

import java.util.List;
import com.revature.models.Account;

public interface AccountDAO {
	public boolean insertAccount(Account account);
	public boolean deleteAccount(int accountID);
	public boolean deleteAllUsersAccounts(int userID);
	public boolean deleteAllAccounts();
	public boolean updateBalance(int account_id, double amount);
	public double getBalance(int accountID);
	public Account getAccount(int accountID);	
	public List<Account> getAllAccounts(int userID);
	boolean accountExists(int accountID, int userID);
}
