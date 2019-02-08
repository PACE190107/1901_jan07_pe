package com.revature.models;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 649049628526815752L;
	private int employeeId;
	private String firstName;
	private String lastName;
	private String username;
	private String password = null;
	private String email;
	private EmployeeType employeeType;
	
	public Employee() {
		
	}
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType type) {
		this.employeeType = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

