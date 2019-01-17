package com.revature.oop;

import java.io.Serializable;

public class Bank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountType accountType;
	private String accountName;
	private double balance;
	private int routingNumber;
	private int accountNumber;
	private String email;
	
	public Bank() {
		super();
	}
	public Bank(AccountType accountType, String accountName, double balance, int routingNumber, int accountNumber,
			String email) {
		super();
		this.accountType = accountType;
		this.accountName = accountName;
		this.balance = balance;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.email = email;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + accountNumber;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + routingNumber;
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
		Bank other = (Bank) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountType != other.accountType)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (routingNumber != other.routingNumber)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bank [accountType=" + accountType + ", accountName=" + accountName + ", balance=" + balance
				+ ", routingNumber=" + routingNumber + ", accountNumber=" + accountNumber + ", email=" + email + "]";
	}
	
	
	
	
}
