package com.revature.model;

public class BankAccount implements AccountInterface {
	private double balance;
	private float interestRate;
	private String AccountName;
	private int accountID;
	public BankAccount(int accountID, String AccountName, double balance, float interestRate) {
		this.accountID = accountID;
		this.AccountName = AccountName;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	public int getAccountID() {
		return accountID;
	}

	public double withdrawl(double withdrawCash) {
			balance -= withdrawCash;
		return balance;
	}

	public double deposit(double depositCash) {
		// TODO Auto-generated method stub
		return balance;
	}

	public boolean addUser() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAccount() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString() {
		return "" + AccountName + ": " + balance + " " + interestRate;
	}

}
