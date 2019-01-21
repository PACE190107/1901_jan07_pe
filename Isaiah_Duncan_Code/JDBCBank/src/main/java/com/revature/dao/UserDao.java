package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	public int getUserIdImpl(User user);
	public boolean insertUser(User user);
	public boolean insertUserProcedure(User user);
	public boolean newUserProcedure(User user);
	public User getUser();
	public boolean isUser(User user);
	public boolean checkUser(User user);
	public List<User> getAllUsers();
	public boolean editChangeUsernameImpl(User user);
	public boolean editChangeFirstnameImpl(User user);
	public boolean editChangeLastnameImpl(User user);
	public boolean deleteUserImpl(User user);
	
}
