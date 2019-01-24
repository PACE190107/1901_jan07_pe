package rev.project0.dao;

import java.util.List;

import rev.project0.models.Account;

public interface AccountDao {
	
	public Account getAccountByAccountId(int accountId);
	public List<Account> getAccountsByUserId(int userId);
	public boolean createAccount(String accountType, int userId);//accounts should be created with an initial value of 0
	public boolean deleteAccount(int accountId);

}
