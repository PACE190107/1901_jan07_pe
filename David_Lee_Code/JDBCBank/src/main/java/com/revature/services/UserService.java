package com.revature.services;


import java.util.List;

import com.revature.dao.UserDAOImp;
import com.revature.models.Account;
import com.revature.models.User;

//Singleton
public class UserService {
	//Private user service variable
	private static UserService user_service;
	//Private constructor 
	private UserService() {
	
	}
	//Initializes an instance of user_service
	public static UserService getUserService() {
		if (user_service == null) {
			user_service = new UserService();
		}
		return user_service;
	}
	public User getSuperUser() {
		return UserDAOImp.getUserDao().getSuperUser();
	}
	//Creates a user
	public boolean createUser(User user) {
		return UserDAOImp.getUserDao().createUser(user);
	}
	
	//Deletes a User
	public boolean deleteUser(User user) {
		return UserDAOImp.getUserDao().deleteUser(user);
	}
	//Create a bank account
	public boolean createAccount(User user, Account acct) {
		return UserDAOImp.getUserDao().createAccount(user,acct);	
	}
	
	//Delete a bank account
	public boolean deleteAccount(Account acct) {
		return UserDAOImp.getUserDao().deleteAccount(acct.getAccountID());
	}

	//Returns a ID from the database
	public int getID(User user) {
		return UserDAOImp.getUserDao().getID(user);
	}
	
	//Returns all accounts possessed by the User
	public List<Account> getAllAccounts(User user) {
		return UserDAOImp.getUserDao().getAllAccounts(user.getUser_id());
	}
	
	//Transfer amounts
	public boolean transferAmount(double amount, int acct_ID) {
		return UserDAOImp.getUserDao().transfer(amount, acct_ID);
	}
	//Find if a username exists in the system
	public boolean findID(User user) {
		return UserDAOImp.getUserDao().findID(user.getUserName());
	}
	//Find a password if it exists in the system
	public String findPass(User user) {
		return UserDAOImp.getUserDao().findPass(user.getUserName());
	}
	
	//Check if a password is the same. 
	public boolean confirmPass(User user) {
		return UserDAOImp.getUserDao().confirmPass(user);
	}
	
	public List<User> getAllUsers() {
		return UserDAOImp.getUserDao().getAllUsers();
	}
	
	public boolean modifyUser(User user, int command){
		return UserDAOImp.getUserDao().modifyUser(user, command);
	}
	
}
