package com.ers.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee implements Serializable {
	private static final long serialVersionUID = 6597226566391263285L;
	
	private int eID;
	private String eUsername;
	@JsonIgnore
	private String ePassword;
	private String eFirstName;
	private String eLastName;
	private String eEmail;
	private boolean isConfirmed;
	private boolean isManager;
	
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public String geteUsername() {
		return eUsername;
	}
	public void seteUsername(String eUsername) {
		this.eUsername = eUsername;
	}
	public String getePassword() {
		return ePassword;
	}
	public void setePassword(String ePassword) {
		this.ePassword = ePassword;
	}
	public String geteFirstName() {
		return eFirstName;
	}
	public void seteFirstName(String eFirstName) {
		this.eFirstName = eFirstName;
	}
	public String geteLastName() {
		return eLastName;
	}
	public void seteLastName(String eLastName) {
		this.eLastName = eLastName;
	}
	public String geteEmail() {
		return eEmail;
	}
	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}
	public boolean isConfirmed() {
		return isConfirmed;
	}
	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	public Employee() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eEmail == null) ? 0 : eEmail.hashCode());
		result = prime * result + ((eFirstName == null) ? 0 : eFirstName.hashCode());
		result = prime * result + eID;
		result = prime * result + ((eLastName == null) ? 0 : eLastName.hashCode());
		result = prime * result + ((ePassword == null) ? 0 : ePassword.hashCode());
		result = prime * result + ((eUsername == null) ? 0 : eUsername.hashCode());
		result = prime * result + (isConfirmed ? 1231 : 1237);
		result = prime * result + (isManager ? 1231 : 1237);
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
		if (eEmail == null) {
			if (other.eEmail != null)
				return false;
		} else if (!eEmail.equals(other.eEmail))
			return false;
		if (eFirstName == null) {
			if (other.eFirstName != null)
				return false;
		} else if (!eFirstName.equals(other.eFirstName))
			return false;
		if (eID != other.eID)
			return false;
		if (eLastName == null) {
			if (other.eLastName != null)
				return false;
		} else if (!eLastName.equals(other.eLastName))
			return false;
		if (ePassword == null) {
			if (other.ePassword != null)
				return false;
		} else if (!ePassword.equals(other.ePassword))
			return false;
		if (eUsername == null) {
			if (other.eUsername != null)
				return false;
		} else if (!eUsername.equals(other.eUsername))
			return false;
		if (isConfirmed != other.isConfirmed)
			return false;
		if (isManager != other.isManager)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [eID=" + eID + ", eUsername=" + eUsername + ", ePassword=" + ePassword + ", eFirstName="
				+ eFirstName + ", eLastName=" + eLastName + ", eEmail=" + eEmail + ", isConfirmed=" + isConfirmed
				+ ", isManager=" + isManager + "]";
	}
}
