package com.revature.services;

import java.util.List;

import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService {

	private static UserService userService;
	
	private UserService() {
		
	}
	
	
	public static UserService getUserService() {
		if(userService == null)
			userService = new UserService();
		return userService;
	}	
	
	public List<User> getAllUserDetails(){
		return UserDaoImplementation.getUserDao().getAllUsers();
	}
	
	public User getUserDetails(String username){
		return UserDaoImplementation.getUserDao().getUser(username);
	}
	
	public boolean registerUser(User user) {
		
		
		return UserDaoImplementation.getUserDao().insertUser(user);
	}
	
	public boolean createUserConnection(User user) {
		
		
		return UserDaoImplementation.getUserDao().createConnection(user);
	}
	
	public boolean checkUsername(String username) {
		
		
		return UserDaoImplementation.getUserDao().userExists(username);
	}
	
	public boolean checkUserID(int userID) {
		
		
		return UserDaoImplementation.getUserDao().userExists(userID);
	}
	
	public boolean checkPassword(String username, String password) {
		
		
		return UserDaoImplementation.getUserDao().verifyPassword(username, password);
	}
	
	public boolean deleteUser(int userID) {
		
		
		return UserDaoImplementation.getUserDao().deleteUser(userID);
	}


	public User getUserDetails(int userID) {
		return UserDaoImplementation.getUserDao().getUser(userID);
	}
	
	public boolean editUserDetails(String oldUsername, String oldPassword, User user) {
		return UserDaoImplementation.getUserDao().editUser(oldUsername, oldPassword, user);
	}
	
	public boolean deleteUserConnection(String username) {
		
		
		return UserDaoImplementation.getUserDao().deleteUserConnection(username);
	}


	public boolean deleteAllUsers() {
		return UserDaoImplementation.getUserDao().deleteAllUsers();
		
	}
}
