package com.jdbcbank.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbcbank.models.Account;

public interface AccountDAOable {
	public boolean createAccount(int userID, String type, Double amount) throws SQLException;
	public List<Account> readAccounts(int userID) throws SQLException;
	public Account readAccount(int accountID) throws SQLException;
	public boolean updateAccount(int accountID, double amount, boolean isDeposit) throws SQLException;
	public boolean deleteAccounts(int userID) throws SQLException;
	public boolean deleteAccount(int accountID) throws SQLException;
}
