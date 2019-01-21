package com.revature.services;

import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService 
{
	private static UserService userService;
	
	private UserService()
	{
		
	}
	
	public static UserService getUserService()
	{
		if(userService == null)
		{
			userService = new UserService();
		}
		return userService;
	}
	
	public static void welcome() 
	{
		UserDaoImplementation.getUserDao().welcome();
	}

	
	public void registerUser(User user)
	{
		UserDaoImplementation.getUserDao().registerUser(user);
	}
	
	public void login()
	{
		UserDaoImplementation.getUserDao().login();
	}
	
	public void connectUser(User user)
	{
		UserDaoImplementation.getUserDao().connectUser(user);
	}
	
	public static User getUser(String userName)
	{
		return UserDaoImplementation.getUserDao().getUser(userName);
	}
	
	public void superMenu()
	{
		UserDaoImplementation.getUserDao().superMenu();
	}
	
	public void updatePassword()
	{
		UserDaoImplementation.getUserDao().updatePassword();
	}
	
	public void deleteUser()
	{
		UserDaoImplementation.getUserDao().deleteUser();
	}
}