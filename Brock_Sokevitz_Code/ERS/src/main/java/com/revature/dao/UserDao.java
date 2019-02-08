package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {

	public boolean insertUser(User user);
	public boolean createConnection(User user);
	public boolean userExists(String username);
	public boolean verifyPassword(String username, String password);
	public User getUser(String username);	
	public List<User> getAllUsers();
	public boolean userExists(int userID);
	public User getUser(int userID);
	public User editUser(User updatedUser, String oldUsername);
	boolean deleteUserConnection(String username);
	boolean deleteAllUsers();
	boolean deleteUser(String username);
	
}
