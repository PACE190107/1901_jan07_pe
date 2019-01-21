package com.revature.dao;

import java.util.List;
import com.revature.models.User;

public interface UserDAO {

	public boolean insertUser(User user);
	public boolean createConnection(User user);
	public boolean userExists(String username);
	public boolean verifyPassword(String username, String password);
	public User getUser(String username);	
	public boolean deleteUser(int userID);
	public List<User> getAllUsers();
	public boolean userExists(int userID);
	public User getUser(int userID);
	boolean editUser(String oldUsername, String oldPassword, User user);
	boolean deleteUserConnection(String username);
	boolean deleteAllUsers();
}
