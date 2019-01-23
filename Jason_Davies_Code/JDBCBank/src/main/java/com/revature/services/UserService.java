package com.revature.services;

import java.util.ArrayList;

import com.revature.dao.UserDAOImplementation;
import com.revature.models.User;

public class UserService {
	
	private static UserService instance;
	
	private UserService() {	}
	public static UserService getUserService() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	public boolean insertUser(User user) {
		return UserDAOImplementation.getUserDAO().insertUser(user);
	}
	
	public boolean updateUser(User user) {
		return UserDAOImplementation.getUserDAO().updateUser(user);
	}
	
	public boolean deleteUser(String username) {
		return UserDAOImplementation.getUserDAO().deleteUser(username);
	}
	
	public User getUser(String userName) {
		return UserDAOImplementation.getUserDAO().getUser(userName);
	}
	
	public ArrayList<User> getAllUsers() {
		return UserDAOImplementation.getUserDAO().getAllUsers();
	}
	
	public boolean createCredentials(User user) {
		return UserDAOImplementation.getUserDAO().createCredentials(user);
	}
	
	public boolean deleteCredentials(User user) {
		return UserDAOImplementation.getUserDAO().deleteCredentials(user);
	}
}
