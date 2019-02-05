package com.revature.services;

import com.revature.models.User;
import com.revautre.dao.UserDaoImplementation;

public class UserService {

	private static UserService custService;

	private UserService() {
	}

	public static UserService getUserService() {
		if (custService == null) {
			custService = new UserService();
		}
		return custService;
	}
	
	
	
	public User goodLogin(String email, String password) {
		return UserDaoImplementation.getCustDao().goodLogin(email, password);
	}
	
	
	
	
}
