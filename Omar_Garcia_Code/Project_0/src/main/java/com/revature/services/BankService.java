package com.revature.services;

import java.sql.SQLException;

import com.revature.Exceptions.DepositFailedException;
import com.revature.dao.BankImplementation;
import com.revature.models.User;

public class BankService {

	private static BankService bank;
	private static User user;
	
	private BankService(User user) {
		BankService.user = user;
	}
	
	public static BankService getBankService(User user) {
		if(bank == null) {
			bank = new BankService(user);
		}
		return bank;
	}
	
	public void deposit(int amount, int account) throws SQLException, DepositFailedException {
		BankImplementation.getBankImplementation().deposite(amount, account, user.getId());
	}
	
	public void withdraw(int amount, int account) throws SQLException, DepositFailedException {
		BankImplementation.getBankImplementation().withdraw(amount, account, user.getId());
	}

	public void getAccounts() throws SQLException {
		BankImplementation.getBankImplementation().getAccounts(user.getId());
		
	}
}

