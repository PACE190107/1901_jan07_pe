package com.revature.dao;

import java.util.ArrayList;

import com.revature.models.User;

interface UserDAO {
	public boolean insertUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String username);
	public User getUser(String username);
	public ArrayList<User> getAllUsers();
	public boolean createCredentials(User user);
	public boolean deleteCredentials(User user);
}
