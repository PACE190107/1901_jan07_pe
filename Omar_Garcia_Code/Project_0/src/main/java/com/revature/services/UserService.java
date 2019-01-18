package com.revature.services;

import java.sql.SQLException;

import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService {
	
	private static UserService user;
	
	private UserService() {
		
	}
	
	public static UserService getUserService() {
		if(user == null) {
			user = new UserService();
		}
		return user;
	}

	public User login(String username, String password) throws SQLException {
		return UserDaoImplementation.getUserDao().login(username,password);
	}
	
	public User register(String first, String last, String username, String password) throws SQLException {
		return UserDaoImplementation.getUserDao().register(first, last, username,password);
	}
}
