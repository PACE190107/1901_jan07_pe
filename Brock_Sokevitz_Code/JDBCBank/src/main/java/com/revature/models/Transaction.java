package com.revature.models;

public class Transaction {
	private int transactionID;
	private int accountID;
	private int userID;
	private double amount;
	public Transaction() {
		super();
	}
	public Transaction(int accountID, int userID, double amount) {
		super();
		this.accountID = accountID;
		this.userID = userID;
		this.amount = amount;
	}
	public Transaction(int transactionID, int accountID, int userID, double amount) {
		super();
		this.transactionID = transactionID;
		this.accountID = accountID;
		this.userID = userID;
		this.amount = amount;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transactionID;
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
		Transaction other = (Transaction) obj;
		if (accountID != other.accountID)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (transactionID != other.transactionID)
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", accountID=" + accountID + ", userID=" + userID
				+ ", amount=" + amount + "]";
	}	
}
