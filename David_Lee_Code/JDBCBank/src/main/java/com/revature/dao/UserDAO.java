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
	
	
	/**
	 * @param user - current instance of the user
	 * @return True or false if user is created
	 */
	public boolean createUser(User user);
	
	
	/**
	 * @param user - current instance of the user
	 * @return true or false if a user is deleted
	 */
	public boolean deleteUser(User user);
	
	
	/**
	 * @param user - The current instance of the user
	 * @param acct - The user's account details
	 * @return true or false if a account was created or not
	 */
	public boolean createAccount(User user, Account acct);
	
	/**
	 * @param acct_id = the account ID that the user is trying to delete
	 * @return true or false whether a account is deleted
	 */
	public boolean deleteAccount(int acct_id);
	
	/**
	 * @param amount = amount to add or subtract
	 * @param acc_ID = account ID that deposits or withdraw
	 * @return true or false if the ID is found
	 */
	public boolean transfer(double amount, int acc_ID);
	
	/**
	 * @param username of the User
	 * @return true or false if the ID is found
	 */
	public boolean findID(String username);
	
	/**
	 * @param username of the User
	 * @return a String to find the encrypted password
	 */
	public String findPass(String username);
	
	/**
	 * @param user = Current instance of the user
	 * @return true or false if the password matches
	 */
	public boolean confirmPass(User user);
	/**
	 * @return Reads file to get super user data
	 */
	public User getSuperUser();
	/**
	 * @param user - Current instance of the user
	 * @param command (1- Changes first name | 2- Changes last name | 3- Changes user name)
	 * @return true or false if the command works
	 */
	public boolean modifyUser(User user, int command);
}
