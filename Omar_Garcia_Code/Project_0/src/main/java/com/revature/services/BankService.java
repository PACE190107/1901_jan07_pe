package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.Exceptions.AccountNotFoundException;
import com.revature.Exceptions.DepositFailedException;
import com.revature.Exceptions.EmptyAccountException;
import com.revature.Exceptions.OverDraftException;
import com.revature.Exceptions.WithDrawException;
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
	
	public void withdraw(int amount, int account) throws SQLException, OverDraftException, AccountNotFoundException, WithDrawException {
		BankImplementation.getBankImplementation().withdraw(amount, account, user.getId());
	}

	public void getAccounts() throws SQLException, EmptyAccountException {
		accounts = BankImplementation.getBankImplementation().getAccounts(user.getId());
	}
	
	public void createAccount(String type, int amount) throws SQLException {
		BankImplementation.getBankImplementation().createAccount(type, amount, user.getId());
	}
	
	public void deleteAccount() {
		
	}
}

