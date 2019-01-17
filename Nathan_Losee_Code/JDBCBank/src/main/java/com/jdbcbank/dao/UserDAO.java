package com.jdbcbank.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.jdbcbank.models.User;
import com.jdbcbank.util.BankErrors;
import com.jdbcbank.util.ConnectionManager;

public class UserDAO implements UserDAOable {
	private static UserDAO userDAO;
	
	private UserDAO() { }
	
	public static UserDAO getUserDAO() {
		if (userDAO == null)
			userDAO = new UserDAO();
		return userDAO;
	}

	@Override
	public boolean createUser(String username, String password) throws SQLException {
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL insert_user(?,?,?)");
		stmnt.setString(1, username);
		stmnt.setString(2, password);
		stmnt.registerOutParameter(3, Types.INTEGER);
		stmnt.executeUpdate();
		
		if (stmnt.getInt(3) <= 0)
			throw new BankErrors.InvalidUsernamePasswordException();
		
		stmnt.close();
		
		return true;
	}

	@Override
	public List<User> readUsers() throws SQLException {
		List<User> users = new ArrayList<User>();
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		ResultSet foundUsers = stmnt.executeQuery("SELECT * FROM User_Accounts");
		
		while (foundUsers.next()) {
			User user = new User();
			user.setUserID(foundUsers.getInt(1));
			user.setUsername(foundUsers.getString(2));
			user.setPassword(foundUsers.getString(3));
			
			users.add(user);
		}

		stmnt.close();
		
		return users;
	}
	@Override
	public User readUser(String username, String password) throws SQLException {
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		ResultSet foundUsers = stmnt.executeQuery(
			"SELECT * FROM user_accounts " +
				"WHERE user_username = '" + username +
				"' AND user_password = '" + password + "'");
		
		if (foundUsers.next()) {
			User user = new User();

			user.setUserID(foundUsers.getInt(1));
			user.setUsername(foundUsers.getString(2));
			user.setPassword(foundUsers.getString(3));

			stmnt.close();
			
			return user;
		} else
			throw new BankErrors.InvalidUsernamePasswordException();
	}

	@Override
	public boolean deleteUser(String username, String password) throws SQLException {
		AccountDAO.getAccountDAO().deleteAccounts(readUser(username, password).getUserID());
		
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL delete_user(?,?,?)");
		stmnt.setString(1, username);
		stmnt.setString(2, password);
		stmnt.registerOutParameter(3, Types.INTEGER);
		stmnt.executeUpdate();
		
		if (stmnt.getInt(3) > 0) {
			stmnt.close();
			return true;
		}
		else
			throw new BankErrors.InvalidUsernamePasswordException();
	}
}
