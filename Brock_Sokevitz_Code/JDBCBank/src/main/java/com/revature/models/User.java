package com.revature.models;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3081557269645010142L;
	
	private int userID;
	private String username;
	private String password;
	private int Superuser;
	public User() {
		super();
	}
	public User(String username, String password, int superuser) {
		super();
		this.username = username;
		this.password = password;
		Superuser = superuser;
	}
	public User(int userID, String username, String password, int superuser) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		Superuser = superuser;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSuperuser() {
		return Superuser;
	}
	public void setSuperuser(int superuser) {
		Superuser = superuser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Superuser;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (Superuser != other.Superuser)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", Superuser="
				+ Superuser + "]";
	}
	
}
	
	