package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	public boolean insertUser(User user);
	public boolean insertUserProcedure(User user);
	public User getUser();
	public List<User> getAllUsers();
	public boolean deleteUserProcedure(User user);
	
}
