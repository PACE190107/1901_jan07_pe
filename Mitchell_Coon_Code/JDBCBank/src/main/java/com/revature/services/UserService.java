package com.revature.services;

import java.util.List;

import com.revature.dao.UserDAOImplementation;
import com.revature.models.User;

public class UserService {

	private static UserService userService;
	private UserService() {
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}

	public boolean registerUserProcedure(User user) {
		return UserDAOImplementation.getUserDAO().insertUserProcedure(user);
	}

	public User getUser(String userName) {
		return UserDAOImplementation.getUserDAO().getUser(userName);
	}
	
	public boolean deposit(double amount, int userID, String accountType) {
		return UserDAOImplementation.getUserDAO().deposit(amount, userID, accountType);
	}

	public boolean withdraw(double amount, int userID, String accountType) {
		return UserDAOImplementation.getUserDAO().withdraw(amount, userID, accountType);
		
	}
	
	public int retrieveID(String userName) {
		return UserDAOImplementation.getUserDAO().retrieveID(userName);
	}

	public double checkBalance(int user_Id, String accountType) {
		return UserDAOImplementation.getUserDAO().checkBalance(user_Id, accountType);
	}

	public void viewBalances(int user_Id) {
		UserDAOImplementation.getUserDAO().viewBalances(user_Id);
	}

	public boolean verifyUser(String username, String pass) {
		return UserDAOImplementation.getUserDAO().verifyUser(username, pass);
	}


	public boolean checkBalanceEmpty(int userId, String account_type) {
		return UserDAOImplementation.getUserDAO().checkBalanceEmpty(userId, account_type);
	}
	
	public boolean checkBalancesEmpty(int userId) {
		return UserDAOImplementation.getUserDAO().checkBalancesEmpty(userId);
	}

	public boolean deleteAccount(int userId, String account_type) {
		return UserDAOImplementation.getUserDAO().deleteAccount(userId, account_type);	
	}
	
	public boolean closeAllAccounts(int userId) {
		return UserDAOImplementation.getUserDAO().closeAllAccounts(userId);
	}

	public boolean addAccount(int user_Id, String account_type) {
		return UserDAOImplementation.getUserDAO().addAccount(user_Id, account_type);
		
	}

	public boolean accountExists(int userId, String account_type) {
		return UserDAOImplementation.getUserDAO().accountExists(userId,account_type);
	}

	public void viewUsers() {
		UserDAOImplementation.getUserDAO().viewUsers();
	}

	public boolean userExists(int user_id) {
		return UserDAOImplementation.getUserDAO().userExists(user_id);
	}

	public boolean updateUser(int user_id, User updatedUser) {
		return UserDAOImplementation.getUserDAO().updateUser(user_id, updatedUser);
	}
	
}