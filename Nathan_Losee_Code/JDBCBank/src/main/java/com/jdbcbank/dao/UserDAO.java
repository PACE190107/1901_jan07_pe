package com.jdbcbank.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
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
		
		try {
			stmnt.executeUpdate();
			if (stmnt.getInt(3) <= 0)
				throw new BankErrors.ExistingUsernameException();
			stmnt.close();
			return true;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new BankErrors.ExistingUsernameException();
		}
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
	public User readUser(int userID) throws SQLException {
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		ResultSet foundUsers = stmnt.executeQuery(
			"SELECT * FROM user_accounts " +
				"WHERE user_id = " + userID);
		
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
	public boolean updateUser(int userID, String oldCredential, String newCredential) throws SQLException {
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL update_user(?,?,?,?)");
		stmnt.setInt(1, userID);
		stmnt.setString(2, oldCredential);
		stmnt.setString(3, newCredential);
		stmnt.registerOutParameter(4, Types.INTEGER);
		
		try {
			stmnt.executeUpdate();
			if (stmnt.getInt(4) <= 0)
				throw new BankErrors.InvalidUsernamePasswordException();
			if (stmnt.getInt(4) == 2)
				throw new BankErrors.ExistingUsernameException();
			stmnt.close();
			return true;
		} catch (SQLSyntaxErrorException e) {
			throw new BankErrors.InvalidUsernamePasswordException();
		}
	}

	@Override
	public boolean deleteUser(String username, String password) throws SQLException {
		AccountDAO.getAccountDAO().deleteAccounts(readUser(username, password).getUserID());
		
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL delete_user(?,?,?)");
		stmnt.setString(1, username);
		stmnt.setString(2, password);
		stmnt.registerOutParameter(3, Types.INTEGER);
		
		try {
			stmnt.executeUpdate();
			if (stmnt.getInt(3) <= 0)
				throw new BankErrors.InvalidUsernamePasswordException();
			stmnt.close();
			return true;
		} catch (SQLSyntaxErrorException e) {
			throw new BankErrors.InvalidUsernamePasswordException();
		}
	}
	@Override
	public boolean deleteUser(int userID) throws SQLException {
		AccountDAO.getAccountDAO().deleteAccounts(readUser(userID).getUserID());
		
		User user = readUser(userID);
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL delete_user(?,?,?)");
		stmnt.setString(1, user.getUsername());
		stmnt.setString(2, user.getPassword());
		stmnt.registerOutParameter(3, Types.INTEGER);
		
		try {
			stmnt.executeUpdate();
			if (stmnt.getInt(3) <= 0)
				throw new BankErrors.InvalidUsernamePasswordException();
			stmnt.close();
			return true;
		} catch (SQLSyntaxErrorException e) {
			throw new BankErrors.InvalidUsernamePasswordException();
		}
	}
}
