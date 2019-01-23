package com.revature.models;
import java.util.ArrayList;

public class User {
	private int id; // primary key
	private String userName; // also unique, but not a PK
	private String password;
	private boolean isSuper;
	private ArrayList<Account> accounts;
	
	public User(String userName, String password, boolean isSuper) {
		super();
		this.userName = userName;
		this.password = password;
		this.isSuper = isSuper;
		accounts = new ArrayList<Account>();
	}
	
	public User(int id, String userName, String password, boolean isSuper) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.isSuper = isSuper;
		accounts = new ArrayList<Account>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + id;
		result = prime * result + (isSuper ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (id != other.id)
			return false;
		if (isSuper != other.isSuper)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", isSuper=" + isSuper
				+ ", accounts=" + accounts + "]";
	}

}