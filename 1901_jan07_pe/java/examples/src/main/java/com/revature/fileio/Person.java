package com.revature.fileio;
import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = -3499168729969111466L;
	private String name = "Yuvi";
	private transient String dateOfBirth = "01-01-2000";
	private transient int ssn = 1234567890;
	private String occupation = "MarvelHero";
	
	public Person() {
		super();
	}
	public Person(String name, String dateOfBirth, int ssn, String occupation) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.ssn = ssn;
		this.occupation = occupation;
	}
	public Person(String name, String dateOfBirth, String occupation) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.occupation = occupation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
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
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
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
		return "Person [name=" + name + ", dateOfBirth=" + dateOfBirth + ", ssn=" + ssn + ", occupation=" + occupation
				+ "]";
	}
	
	
}
