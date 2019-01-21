package com.revature.user;

public class AuthenticatedUser {
	
	
	private String username;
	private String password;
	private int ID;
	
	public AuthenticatedUser() {
		super();
	}
	
	public AuthenticatedUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public AuthenticatedUser(String username, String password, int iD) {
		super();
		this.username = username;
		this.password = password;
		ID = iD;
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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		AuthenticatedUser other = (AuthenticatedUser) obj;
		if (ID != other.ID)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "AuthenticatedUser [username=" + username + ", password=" + password + ", ID=" + ID + "]";
	}

	
	
	
	

}
