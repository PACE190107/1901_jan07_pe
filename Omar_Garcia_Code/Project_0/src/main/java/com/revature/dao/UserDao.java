package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.Exceptions.EmptyUsersException;
import com.revature.Exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDao {
	public List<User> getAllUsers() throws SQLException, EmptyUsersException;
	public boolean insertUser(User user) throws SQLException;
	public boolean insertUserPrecedure(User user);
	public User login(String username, String password) throws SQLException;
	public User getUser(int id) throws SQLException, UserNotFoundException;
	public void updateUser(User user, boolean newPassword) throws SQLException;

}
