package com.revature.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.models.User;

public interface UserDao {
	public User getUser();
	public List<User> getAllUsers();
	public boolean insertUser(User user);
	public boolean insertUserPrecedure(User user);
	public User login(String username, String password) throws SQLException;

}
