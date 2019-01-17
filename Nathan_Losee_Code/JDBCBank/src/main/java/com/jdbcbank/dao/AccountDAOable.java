package com.jdbcbank.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbcbank.models.Account;
import com.jdbcbank.models.AccountType;

public interface AccountDAOable {
	public boolean createAccount(int userID, AccountType type, Double amount) throws SQLException;
	public List<Account> readAccounts(int userID) throws SQLException;
	public Account readAccount(int accountID) throws SQLException;
	public boolean updateAccount(int accountID, double amount) throws SQLException;
	public boolean deleteAccounts(int userID) throws SQLException;
	public boolean deleteAccount(int accountID) throws SQLException;
}
