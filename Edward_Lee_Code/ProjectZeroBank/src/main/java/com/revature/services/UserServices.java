package com.revature.services;

import java.util.List;

import com.revature.BankDAO.BankDAOImpl;
import com.revature.model.*;

public class UserServices {
//basically a duplicate of the DAO file
	private static UserServices userService;
	private UserServices() {
	}
	
	public static UserServices getCustomerService() {
		if (userService == null) {
			userService = new UserServices();
		}
		return userService;
	}
	
	public List<String> getAllUsers(){
		return BankDAOImpl.getBankDao().getAllUsers();
	}
	public BankAccount CreateAccount(String Username, String accountName) {
		return BankDAOImpl.getBankDao().newAccount(Username, accountName);
	}
	public List<BankAccount> getUserAccounts(int userid){
		return BankDAOImpl.getBankDao().getAccounts(userid);
	}
	public userAbstract createUser(String username, String password, String firstName, String lastName, String email) {
		return BankDAOImpl.getBankDao().createUser(new StringBuffer(username), new StringBuffer(password),new StringBuffer(firstName),new StringBuffer( lastName),new StringBuffer(email));
	}
	public userAbstract attemptLogIn(String username, String password) {
		return BankDAOImpl.getBankDao().logIn(new StringBuffer(username), new StringBuffer(password));
	}
	public double withdrawCash(int accountNumber, double withdrawAmount) {
		return BankDAOImpl.getBankDao().accountWithdrawl(accountNumber, withdrawAmount);
	}
	public double depositCash(int accountNumber, double depositAmount) {
		return BankDAOImpl.getBankDao().accountDeposit(accountNumber, depositAmount);
	}public boolean deleteAccount(int accountID) {
		return BankDAOImpl.getBankDao().deleteAccount(accountID);
	}
	public void deleteUser(String deletedUser) {
		BankDAOImpl.getBankDao().deleteUser(deletedUser);
	}
	public void updateUserPassword(String newPassword, String username) {
		BankDAOImpl.getBankDao().updatePassword(username, newPassword);
	}
}