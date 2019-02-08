package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.User;

public interface UserDao {
	
	public User getUser(String username);
	public List<User> getAllUsers() throws SQLException;
}
