package com.revature.services;

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
}

