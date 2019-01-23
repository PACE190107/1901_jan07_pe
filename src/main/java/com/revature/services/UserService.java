package com.revature.services;

import java.util.List;

import com.revature.dao.UserDaoImp;
import com.revature.models.User;

public class UserService {

	private static UserService userService;
	private UserService() {
	}
	
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService();
		}
		return userService;
	}
	
	public List<User> getAllUserDetails() {
		return UserDaoImp.getUserDao().getAllUsers();
	}
	
	public boolean registerUser(User user) {
		return UserDaoImp.getUserDao().insertUser(user);
	}
	
	public boolean registerUserProcedure(User user) {
		return UserDaoImp.getUserDao().insertUserProcedure(user);
	}
	
	public boolean removeUserProcedure(User user) {
		return UserDaoImp.getUserDao().deleteUserProcedure(user);
	}
	
	public User getUser() {
		return UserDaoImp.getUserDao().getUser();
	}
}
