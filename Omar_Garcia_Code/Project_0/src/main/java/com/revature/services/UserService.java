package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Exceptions.EmptyUsersException;
import com.revature.Exceptions.UserNotFoundException;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService {

	private static UserService user;

	private UserService() {

	}

	public static UserService getUserService() {
		if (user == null) {
			user = new UserService();
		}
		return user;
	}

	public User login(String username, String password) throws SQLException {
		return UserDaoImplementation.getUserDao().login(username, password);
	}

	public User register(String first, String last, String username, String password) throws SQLException {
		return UserDaoImplementation.getUserDao().register(first, last, username, password);
	}

	public void viewAllUsers() throws SQLException, EmptyUsersException {
		List<User> users = new ArrayList<>();
		users = UserDaoImplementation.getUserDao().getAllUsers();
		
		for(User user : users) {
			user.toString();
		}
	}
	
	public void deleteAllUsers() throws SQLException {
		UserDaoImplementation.getUserDao().deleteAllUsers();
	}

	public User getUser(int selection) throws SQLException, UserNotFoundException {
		return UserDaoImplementation.getUserDao().getUser(selection);
	}

	public void updateUser(User user2, boolean newPassword) throws SQLException {
		UserDaoImplementation.getUserDao().updateUser(user2, newPassword);
	}
}
