package com.revature.model;

import java.util.List;

public abstract class userAbstract {
	private StringBuffer username;
	private StringBuffer firstName;
	private StringBuffer lastName;
	private boolean admin = false;
	private int userId;
	List<BankAccount> bankAccounts;
	
public userAbstract(StringBuffer username, StringBuffer firstName, StringBuffer lastName, int userId,
			List<BankAccount> bankAccounts) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.bankAccounts = bankAccounts;
	}
//-----------------------------------------------------------
	public StringBuffer getUsername() {
		return username;
	}
	public void setUsername(StringBuffer username) {
		this.username = username;
	}
	
//------------------------------------------------------------
	public StringBuffer getFirstName() {
		return firstName;
	}
	public void setFirstName(StringBuffer firstName) {
		this.firstName = firstName;
	}

//------------------------------------------------------------
	public StringBuffer getLastName() {
		return lastName;
	}
	public void setLastName(StringBuffer lastName) {
		this.lastName = lastName;
	}

//--------------------------------------------------------------
	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

//--------------------------------------------------------------
	public void addBankAccount(BankAccount ba) {
		bankAccounts.add(ba);
	}
	
//--------------------------------------------------------------
	public boolean removeBankAccount(BankAccount ba) {
		return false; //false if it fails true if it succeeds
	}
	
//--------------------------------------------------------------
	public int getUserID() {
		return userId;
	}

//--------------------------------------------------------------
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin() {
		admin = true;
	}
	
//--------------------------------------------------------------
	@Override
	public String toString() {
		return "userAbstract [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", bankAccounts=" + bankAccounts + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		userAbstract o1 = (userAbstract)o;
		System.out.println(this.getUsername());
		
		if(o1.getUsername().toString().equals(this.getUsername().toString())) {
			return true;
		}
		return false;
		
	}
}
