package com.revature.models;

public class Accounts {

	private int bank_account_id;
	private int bank_account_balance;
	private int user_id;
	
	public Accounts() {
		super();
	}

	
	
	public Accounts(int bank_account_id) {
		super();
		this.bank_account_id = bank_account_id;
	}



	public Accounts(int user_id, int bank_account_balance) {
		super();
		this.bank_account_balance = bank_account_balance;
		this.user_id = user_id;
	}

	public Accounts(int bank_account_id, int bank_account_balance, int user_id) {
		super();
		this.bank_account_id = bank_account_id;
		this.bank_account_balance = bank_account_balance;
		this.user_id = user_id;
	}

	public int getBank_account_id() {
		return bank_account_id;
	}

	public void setBank_account_id(int bank_account_id) {
		this.bank_account_id = bank_account_id;
	}

	public int getBank_account_balance() {
		return bank_account_balance;
	}

	public void setBank_account_balance(int bank_account_balance) {
		this.bank_account_balance = bank_account_balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bank_account_balance;
		result = prime * result + bank_account_id;
		result = prime * result + user_id;
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
		Accounts other = (Accounts) obj;
		if (bank_account_balance != other.bank_account_balance)
			return false;
		if (bank_account_id != other.bank_account_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Accounts [bank_account_id=" + bank_account_id + ", bank_account_balance=" + bank_account_balance
				+ ", user_id=" + user_id + "]";
	}
	
	
}
