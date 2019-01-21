package com.revature.models;

public class Account {
	private int accountID;
	private int userID;
	private String accountType;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(int userID, String accountType, double balance) {
		super();
		this.userID = userID;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	public Account(int accountID, int userID, String accountType, double balance) {
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.accountType = accountType;
		this.balance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userID;
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
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", userID=" + userID + ", accountType=" + accountType + ", balance="
				+ balance + "]";
	}
	
	
}
