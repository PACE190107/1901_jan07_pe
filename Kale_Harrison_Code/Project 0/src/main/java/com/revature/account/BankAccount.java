package com.revature.account;

public class BankAccount {

	

	private int accountId;
	private String accountType;
	private double balance;
	private int userId;

	
	public BankAccount() {
		super();
	}
	
	public BankAccount( String accountType, int accountId) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
	}
	/*
	public BankAccount(int accountId, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
	}
	*/
	public BankAccount(String accountType, int accountId, double balance) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	
	public BankAccount(int accountId, String accountType, double balance, int userId) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
		this.userId = userId;
	}
	/*
	public BankAccount(String accountType, int accountId,  double balance, int userId) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.balance = balance;
		this.userId = userId;
	}
*/
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountId != other.accountId)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance
				+ ", userId=" + userId + "]";
	}
	
	
	
	
	
	
}

