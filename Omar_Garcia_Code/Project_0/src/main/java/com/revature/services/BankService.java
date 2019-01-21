package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.Exceptions.AccountNotFoundException;
import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.EmptyAccountException;
import com.revature.Exceptions.OverDraftException;
import com.revature.Exceptions.WithdrawException;
import com.revature.dao.BankImplementation;
import com.revature.models.User;

public class BankService {

	private static BankService bank;
	private static User user;
	private static List<Integer> accounts;
	
	private BankService(User user) {
		BankService.user = user;
	}
	
	public static BankService getBankService(User user) {
		if(bank == null) {
			bank = new BankService(user);
		}
		return bank;
	}
	
	public static User getCurrentUser() {
		if(user == null) {
			user = new User();
		}
		return user;
	}
	
	public boolean isAnAccount(int accountID) throws AccountNotFoundException {
		if(accounts.contains(accountID)) {
		return true;
		}
		else {
			throw new AccountNotFoundException();
		}
	}
	
	public void deposit(int amount, int account) throws SQLException, DepositFailedException {
		BankImplementation.getBankImplementation().deposite(amount, account, user.getId());
	}
	
	public void withdraw(int amount, int account) throws SQLException, OverDraftException, AccountNotFoundException, WithdrawException {
		BankImplementation.getBankImplementation().withdraw(amount, account, user.getId());
	}

	public void getAccounts(int command) throws SQLException, EmptyAccountException {
		accounts = BankImplementation.getBankImplementation().getAccounts(user.getId(), command);
	}
	
	public void createAccount(String type, int amount) throws SQLException {
		BankImplementation.getBankImplementation().createAccount(type, amount, user.getId());
	}
	
	public void deleteAccount(int account) throws SQLException {
		BankImplementation.getBankImplementation().deleteAccount(account, user.getId());
	}
	
	public int superUser() {
		return user.isSuperuser();
	}
}

