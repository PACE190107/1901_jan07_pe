package com.jdbcbank.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbcbank.models.User;

public interface UserDAOable {
	public boolean createUser(String username, String password) throws SQLException;
	public List<User> readUsers() throws SQLException;
	public User readUser(String username, String password) throws SQLException;
	public User readUser(int userID) throws SQLException;
	public boolean updateUser(int userID, String oldCredential, String newCredential) throws SQLException;
	public boolean deleteUser(String username, String password) throws SQLException;
	public boolean deleteUser(int userID) throws SQLException;
}