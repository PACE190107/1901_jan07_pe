package com.proj0.models;

public class Customer {
	
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String userName;
	private int startDeposit;
	private int currentBalance;
	private int bankAccountId;


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Customer(int id, String firstName, String lastName, String userName, String password, int startDeposit) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userName = userName;
		this.startDeposit = startDeposit;
	}
	
	public Customer(int id, String firstName, String lastName, String userName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userName = userName;
	}
	
	public Customer(int id, int currentBalance, int bankAccountId) {
		super();
		this.id = id;
		this.currentBalance = currentBalance;
		this.bankAccountId = bankAccountId;
	}


	public int getBankAccountId() {
		return bankAccountId;
	}


	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}


	public int getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}


	public Customer() {
		super();
	}


	public Customer(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Customer(int id, String firstName, String lastName, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bankAccountId;
		result = prime * result + currentBalance;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + startDeposit;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Customer other = (Customer) obj;
		if (bankAccountId != other.bankAccountId)
			return false;
		if (currentBalance != other.currentBalance)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (startDeposit != other.startDeposit)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", userName=" + userName + ", startDeposit=" + startDeposit + ", currentBalance=" + currentBalance
				+ ", bankAccountId=" + bankAccountId + "]";
	}


	public int getStartDeposit() {
		return startDeposit;
	}


	public void setStartDeposit(int startDeposit) {
		this.startDeposit = startDeposit;
	}
	
}
