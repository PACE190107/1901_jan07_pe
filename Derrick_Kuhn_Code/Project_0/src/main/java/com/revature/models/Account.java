package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8996717747755194152L;
	private int accountNumber;
	private double balance;
	private int userID;
	private String accountName;
	final double START_BALANCE = 0;
	
	public Account() {
		super();
	}

	public Account(int userID, String accountName) {
		super();
		this.userID = userID;
		this.balance = START_BALANCE;
		this.accountName = accountName;
	}

	public Account(int accountNumber, double balance, int userID, String accountName) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userID = userID;
		this.accountName = accountName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(START_BALANCE);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + accountNumber;
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
		if (Double.doubleToLongBits(START_BALANCE) != Double.doubleToLongBits(other.START_BALANCE))
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", userID=" + userID
				+ ", accountName=" + accountName + ", START_BALANCE=" + START_BALANCE + "]";
	}
}
