package com.revature.models;

public class User 
{
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	public User() 
	{
		super();
	}

	public User(String firstName, String lastName, String userName, String password) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public User(int userId, String firstName, String lastName, String userName, String password) 
	{
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + userId;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		User other = (User) obj;
		if (firstName == null) 
		{
			if (other.firstName != null)
				return false;
		} 
		else if (!firstName.equals(other.firstName))
			return false;
		
		if (userId != other.userId)
			return false;
		
		if (lastName == null) 
		{
			if (other.lastName != null)
				return false;
		} 
		else if (!lastName.equals(other.lastName))
			return false;
		
		if (password == null) 
		{
			if (other.password != null)
				return false;
		} 
		else if (!password.equals(other.password))
			return false;
		
		if (userName == null) 
		{
			if (other.userName != null)
				return false;
		} 
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + "]";
	}	
}