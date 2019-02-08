package com.revature.fileio;

import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	public Person(String name, String DateOfBirth, String occupation) {
		super();
		this.name = name;
		this.DateOfBirth = DateOfBirth;
		this.occupation = occupation;
	}
	public Person() {
		super();
	}
	public Person(String name, String DateOfBirth, int ssn, String occupation) {
		super();
		this.name = name;
		this.DateOfBirth = DateOfBirth;
		this.ssn = ssn;
		this.occupation = occupation;
	}
	//make transient to keep members private
	private transient String name = "Josh";
	private transient String DateOfBirth = "10/02/1987";
	private transient int ssn = 123456789;
	private transient String occupation = "Hero";
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DateOfBirth == null) ? 0 : DateOfBirth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + ssn;
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
		Person other = (Person) obj;
		if (DateOfBirth == null) {
			if (other.DateOfBirth != null)
				return false;
		} else if (!DateOfBirth.equals(other.DateOfBirth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		if (ssn != other.ssn)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", DateOfBirth=" + DateOfBirth + ", ssn=" + ssn + ", occupation=" + occupation
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
}

