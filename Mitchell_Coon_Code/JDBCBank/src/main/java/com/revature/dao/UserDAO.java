package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {
	
	public boolean insertUserProcedure(User user);
	public boolean deposit(double amount, int userID, String accountType);
	public boolean withdraw(double amount, int userID, String accountType);
	public void viewBalances(int user_Id);
	public void viewTransactions(String account_type, int user_id);
	public boolean deleteAccount(int user_Id, String account_type);
	public User getUser(String userName);
	public int retrieveID(String userName);
	public double checkBalance(int user_Id, String accountType);
	public boolean verifyUser(String username, String pass);
	public boolean checkBalancesEmpty(int userId);
	public boolean addAccount(int user_Id, String account_type);
	public boolean accountExists(int userId, String account_type);
	public boolean closeAllAccounts(int userId);
	public boolean checkBalanceEmpty(int userId, String account_type);
	public void viewUsers();
	public boolean userExists(int user_id);
	public boolean updateUser(int user_id, User updatedUser);
	int retrieveAccountID(int user_id, String account_type);
	
}