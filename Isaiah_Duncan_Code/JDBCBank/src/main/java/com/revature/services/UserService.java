package com.revature.services;

import java.util.List;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.AdminDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Account;
import com.revature.models.Admin;
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
	
	/************************************************************************/
	// REGISTRATION RELATED METHODS
	/************************************************************************/
	
	//checks if username is already in database
	public boolean registerCheck(User user) { 
		return UserDaoImpl.getUserDao().isUser(user); 
	}
	
	public boolean registerUser(User user) {
		return UserDaoImpl.getUserDao().insertUser(user);
	}
	
	//registers a new user
	public boolean registerUserProcedure(User user) {
		return UserDaoImpl.getUserDao().newUserProcedure(user);
	}
	
	//creates a new checking account on registration
	public boolean addNewChecking(User user) {
		return AccountDaoImpl.getAccountDao().addNewChecking(user);
	}
	
	/************************************************************************/
	// LOGIN RELATED METHODS
	/************************************************************************/
	
	//checks if login credentials are good
	public boolean loginCheck(User user) {
		return UserDaoImpl.getUserDao().checkUser(user);
	}
	
	//gets user's user id 
	public int getUserId(User user) {
		return UserDaoImpl.getUserDao().getUserIdImpl(user);
	}
	
	/************************************************************************/
	// ACCOUNT RELATED METHODS
	/************************************************************************/
	
	//displays all of the user's accounts
	public List<Account> displayUserAccounts(User user) {
		return AccountDaoImpl.getAccountDao().displayUserAccountsImpl(user);
		
	}
	
	public boolean withdrawFromAccount(Account acct) {
		return AccountDaoImpl.getAccountDao().withdrawFromAccountImpl(acct);
		
	}
	
	public boolean depositFromAccount(Account acct) {
		return AccountDaoImpl.getAccountDao().depositFromAccountImpl(acct);
	}

	public boolean addCheckingAcct(Account acct) {
		return AccountDaoImpl.getAccountDao().addCheckingAcctImpl(acct);
	}
	
	public boolean addSavingsAcct(Account acct) {
		return AccountDaoImpl.getAccountDao().addSavingsAcctImpl(acct);
	}
	
	public boolean removeAccount(Account acct) {
		return AccountDaoImpl.getAccountDao().removeAccountImpl(acct);
	}
	
	
	
	/************************************************************************/
	// ADMIN RELATED METHODS
	/************************************************************************/

	public boolean adminCheck(Admin admin) {
		return AdminDaoImpl.getAdminDao().adminCheckImpl(admin);
	}
	
	public List<User> getAllUserDetails() {
		return UserDaoImpl.getUserDao().getAllUsers();
	}
	
	public User getTheUser(User user) {
		return UserDaoImpl.getUserDao().getUser();
	}

	public boolean editChangeUsername(User user) {
		return UserDaoImpl.getUserDao().editChangeUsernameImpl(user);
	}

	public boolean editChangeFirstname(User user) {
		return UserDaoImpl.getUserDao().editChangeFirstnameImpl(user);
	}

	public boolean editChangeLastname(User user) {
		return UserDaoImpl.getUserDao().editChangeLastnameImpl(user);
	}
	
	public boolean deleteUser(User user) {
		return UserDaoImpl.getUserDao().deleteUserImpl(user);
	}
	
}
