package com.revature.model;

public class Employee {
	private int e_id;
	private String e_username;
	private String e_password;
	private String e_firstName;
	private String e_lastName;
	private String e_email;
	private int isManager;
	
	public Employee() {
		super();
	}

	public Employee(int e_id, String e_username, String e_password, String e_firstName, String e_lastNAme,
			String e_email, int isManager) {
		super();
		this.e_id = e_id;
		this.e_username = e_username;
		this.e_password = e_password;
		this.e_firstName = e_firstName;
		this.e_lastName = e_lastNAme;
		this.e_email = e_email;
		this.isManager = isManager;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public String getE_username() {
		return e_username;
	}

	public void setE_username(String e_username) {
		this.e_username = e_username;
	}

	public String getE_password() {
		return e_password;
	}

	public void setE_password(String e_password) {
		this.e_password = e_password;
	}

	public String getE_firstName() {
		return e_firstName;
	}

	public void setE_firstName(String e_firstName) {
		this.e_firstName = e_firstName;
	}

	public String getE_lastNAme() {
		return e_lastName;
	}

	public void setE_lastNAme(String e_lastNAme) {
		this.e_lastName = e_lastNAme;
	}

	public String getE_email() {
		return e_email;
	}

	public void setE_email(String e_email) {
		this.e_email = e_email;
	}

	public int isManager() {
		return isManager;
	}

	public void setManager(int isManager) {
		this.isManager = isManager;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e_email == null) ? 0 : e_email.hashCode());
		result = prime * result + ((e_firstName == null) ? 0 : e_firstName.hashCode());
		result = prime * result + e_id;
		result = prime * result + ((e_lastName == null) ? 0 : e_lastName.hashCode());
		result = prime * result + ((e_password == null) ? 0 : e_password.hashCode());
		result = prime * result + ((e_username == null) ? 0 : e_username.hashCode());
		result = prime * result + isManager;
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
		Employee other = (Employee) obj;
		if (e_email == null) {
			if (other.e_email != null)
				return false;
		} else if (!e_email.equals(other.e_email))
			return false;
		if (e_firstName == null) {
			if (other.e_firstName != null)
				return false;
		} else if (!e_firstName.equals(other.e_firstName))
			return false;
		if (e_id != other.e_id)
			return false;
		if (e_lastName == null) {
			if (other.e_lastName != null)
				return false;
		} else if (!e_lastName.equals(other.e_lastName))
			return false;
		if (e_password == null) {
			if (other.e_password != null)
				return false;
		} else if (!e_password.equals(other.e_password))
			return false;
		if (e_username == null) {
			if (other.e_username != null)
				return false;
		} else if (!e_username.equals(other.e_username))
			return false;
		if (isManager != other.isManager)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [e_id=" + e_id + ", e_username=" + e_username + ", e_password=" + e_password + ", e_firstName="
				+ e_firstName + ", e_lastNAme=" + e_lastName + ", e_email=" + e_email + ", isManager=" + isManager
				+ "]";
	}

	public String getE_lastName() {
		return e_lastName;
	}

	public void setE_lastName(String e_lastName) {
		this.e_lastName = e_lastName;
	}

	
	
	
	
	
}

