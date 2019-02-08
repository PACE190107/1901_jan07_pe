package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revatue.dao.UserDaoImplementation;
import com.revature.models.User;

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
	
	public ArrayList<User> viewEmployees(){
		return UserDaoImplementation.getCustDao().viewEmployees();
	}
	
	public User updateUser(int e_id, String firstName, String lastName, String email) {
		return UserDaoImplementation.getCustDao().updateUser(e_id, firstName, lastName, email);
	} ;
		
	
	
	
}	

