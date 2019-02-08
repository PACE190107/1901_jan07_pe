package com.revature.model;

public class Employee {

	private int empId;
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	private String isManager;
	public Employee() {
		super();
	}
	
	public Employee(int empId) {
		super();
		this.empId = empId;
	}
	
	public Employee(String firstname, String lastname, String username, String email, String isManager) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.isManager = isManager;
	}

	public Employee(int empId, String firstname, String lastname, String username, String email, String isManager) {
		super();
		this.empId = empId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.isManager = isManager;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + empId;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (empId != other.empId)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (isManager == null) {
			if (other.isManager != null)
				return false;
		} else if (!isManager.equals(other.isManager))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
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
		return "Employee [empId=" + empId + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", email=" + email + ", isManager=" + isManager + "]";
	}
	
	
}
