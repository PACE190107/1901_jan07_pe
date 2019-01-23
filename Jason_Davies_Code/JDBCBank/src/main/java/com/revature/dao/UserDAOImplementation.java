package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

public class UserDAOImplementation implements UserDAO {
	
	private static UserDAOImplementation instance;
	static Logger logger = Logger.getLogger(UserDAOImplementation.class);
	
	private UserDAOImplementation() {}
	
	public static UserDAOImplementation getUserDAO() {
		if (instance == null) {
			instance = new UserDAOImplementation();
		}
		return instance;
	}

	@Override
	public boolean insertUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			// stored procedure
			String sql = "CALL INSERT_USER_ACCOUNT('" 
					+ user.getUserName() + "','" 
					+ user.getPassword() + "','"
					+ (user.getIsSuper() ? '1' : '0') + "', ?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			boolean success = (cs.getInt(1) > 0);
			cs.close();
			connection.close();
			if (success) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			logger.error("Failed to insert user - " + user.toString() + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean updateUser(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE USER_ACCOUNT SET U_USERNAME = '" 
					+ user.getUserName() + "', U_PASSWORD = '" 
					+ user.getPassword() + "', U_IS_SUPER = '"
					+ (user.getIsSuper() ? '1' : '0') + "' WHERE U_ID = " 
					+ user.getId();
			Statement stmt = connection.createStatement();
			int results = stmt.executeUpdate(sql);
			if (results > 0) {
				stmt.close();
				connection.close();
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			logger.error("Failed to update user - " + user.toString() + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteUser(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM USER_ACCOUNT WHERE U_USERNAME = '" + username + "'";
			Statement stmt = connection.createStatement();
			int results = stmt.executeUpdate(sql);
			if (results > 0) {
				stmt.close();
				connection.close();
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			logger.error("Failed to delete from user with username - " + username + " - because of " + e.getMessage());
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USER_ACCOUNT WHERE U_USERNAME = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet results = ps.executeQuery();
			if (results.next()) {
				User user = new User(
						results.getInt("U_ID"),
						results.getString("U_USERNAME"), 
						results.getString("U_PASSWORD"),
						results.getBoolean("U_IS_SUPER")
						);
				results.close();
				ps.close();
				connection.close();
				return user;
			}
		} catch (SQLException e) {
			logger.error("Failed to get user with username - " + username + " because of " + e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USER_ACCOUNT";		
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ArrayList<User> users = new ArrayList<>();
			while (results.next()) {
				users.add(new User(
						results.getInt("U_ID"),
						results.getString("U_USERNAME"), 
						results.getString("U_PASSWORD"),
						results.getBoolean("U_IS_SUPER")
						));
			}
			results.close();
			stmt.close();
			connection.close();
			return users;
		} catch (SQLException e) {
			logger.error("Failed to get all users because of " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean createCredentials(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "CREATE USER " + user.getUserName() + " IDENTIFIED BY " + user.getPassword();	
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
			if (user.getIsSuper()) {
				sql = "GRANT DBA TO " + user.getUserName();	
				stmt.execute(sql);
			} else {
				sql = "GRANT CREATE SESSION TO " + user.getUserName();
				stmt.execute(sql);
				sql = "GRANT SELECT, INSERT, UPDATE, DELETE ON BANK_ACCOUNT TO " + user.getUserName();
				stmt.execute(sql);
				sql = "GRANT SELECT ON BANK_ACCOUNT_SEQ TO " + user.getUserName();
				stmt.execute(sql);
			}
			stmt.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			logger.error("Failed to create credentials for - " + user.getUserName() + " because " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteCredentials(User user) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DROP USER " + user.getUserName();		
			Statement stmt = connection.createStatement();
			stmt.execute(sql);		
			return true;
		} catch (SQLException e) {
			logger.error("Failed to delete credentials for - " + user.getUserName() + " because " + e.getMessage());
		}
		return false;
	}
}
