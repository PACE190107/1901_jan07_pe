package com.revature.dao;

import com.revature.models.User;

public interface UserDAO 
{
	public void welcome();
	public User getUser(String userName);
	public void registerUser(User user);
	public void login();
	public void connectUser(User user);
	public void superMenu();
	public void updatePassword();
	public void deleteUser();
}