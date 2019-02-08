package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private int superuser;

	public User() {
		super();
	}

	public User(String email, String password, int superuser) {
		super();
		this.email = email;
		this.password = password;
		this.superuser = superuser;
	}

	public User(String username, String email, String password, int superuser) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.superuser = superuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSuperuser() {
		return superuser;
	}

	public void setSuperuser(int superuser) {
		this.superuser = superuser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + superuser;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (superuser != other.superuser)
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
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", superuser=" + superuser
				+ "]";
	}
}
