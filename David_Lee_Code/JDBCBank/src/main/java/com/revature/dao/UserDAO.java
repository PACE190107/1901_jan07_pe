package com.revature.dao;

import com.revature.models.Account;
import com.revature.models.User;

public interface UserDAO {
//	A User can: 
//		-Access an Account
//		-Modify an Account
//		-Delete an Account (if Empty)
//		-Create an Account
//	A SuperUser can do all users plus:
//		-See all Accounts
	
	
	//Create an User account
	public boolean createUser(User user);
	
	//Delete User
	public boolean deleteUser(User user);
	
	//Create an Account 
	public boolean createAccount(User user, Account acct);
	
	//Delete an Account
	public boolean deleteAccount(int acct_id);
	
	//Deposit and withdrawal
	public boolean transfer(double amount, int acc_ID);
	
	//Get the user account
	public boolean findID(String username);
	
	//Get the password
	public String findPass(String username);
	
	//Checking password
	public boolean confirmPass(User user);
	//Gets super user info
	public User getSuperUser();
	//Modify User
	public boolean modifyUser(User user, int command);
}
