package com.jdbcbank.services;

import java.sql.SQLException;
import java.util.List;

import com.jdbcbank.dao.UserDAO;
import com.jdbcbank.models.User;

public class UserServices {
	public static boolean createUser(String username, String password) throws SQLException {
		return UserDAO.getUserDAO().createUser(username, password);
	}
	public static List<User> readUsers() throws SQLException {
		return UserDAO.getUserDAO().readUsers();
	}
	public static User readUser(String username, String password) throws SQLException {
		return UserDAO.getUserDAO().readUser(username, password);
	}
	public static boolean deleteUser(String username, String password) throws SQLException {
		return UserDAO.getUserDAO().deleteUser(username, password);
	}
}
