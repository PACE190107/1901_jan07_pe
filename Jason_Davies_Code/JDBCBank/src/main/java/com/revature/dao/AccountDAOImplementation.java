package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.utilities.ConnectionUtil;

public class AccountDAOImplementation implements AccountDAO {
	
	private static AccountDAOImplementation instance;
	final static Logger logger = Logger.getLogger(AccountDAOImplementation.class);
	
	private AccountDAOImplementation() {}
	
	public static AccountDAOImplementation getAccountDAO() {
		if (instance == null) {
			instance = new AccountDAOImplementation();
		}
		return instance;
	}

	@Override
	public boolean insertAccount(Account account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO BANK_ACCOUNT values(?,NULL,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, account.getUserId());
			ps.setString(2, account.getName());
			ps.setString(3, account.getType().toString());
			ps.setDouble(4, account.getBalance());
			if (ps.executeUpdate() <= 0) {
				throw new SQLException();
			}
			ps.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			logger.error("Failed to insert - " + account.toString() + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean updateAccount(Account account) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE BANK_ACCOUNT SET "
					+ "B_NAME = '" + account.getName() 
					+ "', B_TYPE = '" + account.getType().toString()
					+ "', B_BALANCE = " + account.getBalance() 
					+ " WHERE B_ID = " + account.getId();
			Statement stmt = connection.createStatement();
			int results = stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			if (results > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("Failed to update - " + account.toString() + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteAccount(int accountId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM BANK_ACCOUNT WHERE B_ID = " + accountId;
			Statement stmt = connection.createStatement();
			int results = stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			if (results > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("Failed to remove bank account with id - " + accountId + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteAllAccounts(int userId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM BANK_ACCOUNT WHERE U_ID = " + userId;
			Statement stmt = connection.createStatement();
			int results = stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
			if (results > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("Failed to remove bank account with user id - " + userId + " because of " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public ArrayList<Account> getAllAccounts(int userId) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE U_ID = " + userId;		
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ArrayList<Account> accounts = new ArrayList<>();
			while (results.next()) {
				accounts.add(new Account(
						results.getInt("B_ID"),
						results.getInt("U_ID"),
						results.getString("B_NAME"),
						AccountType.valueOf(results.getString("B_TYPE")),
						results.getDouble("B_BALANCE")));
			}
			results.close();
			stmt.close();
			connection.close();
			return accounts;
		} catch (SQLException e) {
			logger.error("Failed to get all bank account with id - " + userId + " because of " + e.getMessage());
		}
		return null;
	}
}
