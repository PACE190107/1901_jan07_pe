package com.revature.projectzero.dao;

import java.util.List;

import com.revature.projectzero.models.User;

public interface UserDao {
	public boolean insertCustomerProcedure(User user);
	public User getUser();
	public List<User> getAllUsers();
	boolean registerUser(User user);	
}
