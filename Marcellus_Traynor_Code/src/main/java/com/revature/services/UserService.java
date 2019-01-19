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
	
	public void userMenu()
	{
		UserDaoImplementation.getUserDao().userMenu();
	}
	
//	public User getUser()
//	{
//		return UserDaoImplementation.getUserDao().getUser();
//	}
}