package com.revature.dao;

import com.revature.models.User;

public interface UserDAO 
{
	public void welcome();
	//public User getUser();
	public void registerUser(User user);
	public void login();
	public void userMenu();
}