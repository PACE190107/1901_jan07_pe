package com.revature.services;

import com.revature.dao.AccountsDaoImplementation;
import com.revature.models.User;

public class AccountsService 
{
	private static AccountsService accountsService;
	
	private AccountsService()
	{
		
	}
	
	public static AccountsService getAccountsService()
	{
		if(accountsService == null)
		{
			accountsService = new AccountsService();
		}
		return accountsService;
	}
	
	public static void accountsMenu(User user) 
	{
		AccountsDaoImplementation.accountsMenu(user);
	}
	
	public static void newAccount(User user)
	{
		AccountsDaoImplementation.newAccount(user);
	}
	
	public static void deleteAccount(User user)
	{
		AccountsDaoImplementation.deleteAccount(user);
	}
	
	public static void withdrawDeposit()
	{
		AccountsDaoImplementation.withdrawDeposit();
	}
}