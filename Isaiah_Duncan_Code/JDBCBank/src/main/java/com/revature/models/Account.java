package com.revature.models;

public class Account {
	
	private int accountID;
	private String accountNum;
	private String accountType;
	private double accountBal;
	
	public Account() {
		super();
	}

	public Account(int accountID) {
		super();
		this.accountID = accountID;
	}

	public Account(int accountID, String accountNum) {
		super();
		this.accountID = accountID;
		this.accountNum = accountNum;
	}

	public Account(int accountID, String accountNum, double accountBal) {
		super();
		this.accountID = accountID;
		this.accountNum = accountNum;
		this.accountBal = accountBal;
	}

	public Account(int accountID, String accountNum, String accountType, double accountBal) {
		super();
		this.accountID = accountID;
		this.accountNum = accountNum;
		this.accountType = accountType;
		this.accountBal = accountBal;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountID;
		result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
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
		if (Double.doubleToLongBits(accountBal) != Double.doubleToLongBits(other.accountBal))
			return false;
		if (accountID != other.accountID)
			return false;
		if (accountNum == null) {
			if (other.accountNum != null)
				return false;
		} else if (!accountNum.equals(other.accountNum))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountNum=" + accountNum + ", accountType=" + accountType
				+ ", accountBal=" + accountBal + "]";
	}
	
	
	

}
