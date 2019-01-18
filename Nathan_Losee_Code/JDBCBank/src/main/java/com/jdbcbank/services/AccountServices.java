package com.jdbcbank.services;

import java.sql.SQLException;
import java.util.List;

import com.jdbcbank.dao.AccountDAO;
import com.jdbcbank.models.Account;

public class AccountServices {
	public static boolean createAccount(int userID, String type, Double amount) throws SQLException {
		return AccountDAO.getAccountDAO().createAccount(userID, type, amount);
	}
	public static List<Account> readAccounts(int userID) throws SQLException {
		return AccountDAO.getAccountDAO().readAccounts(userID);
	}
	public static Account readAccount(int accountID) throws SQLException {
		return AccountDAO.getAccountDAO().readAccount(accountID);
	}
	public static boolean updateAccount(int accountID, double amount, boolean isDeposit) throws SQLException {
		return AccountDAO.getAccountDAO().updateAccount(accountID, amount, isDeposit);
	}
	public static boolean deleteAccounts(int userID) throws SQLException {
		return AccountDAO.getAccountDAO().deleteAccounts(userID);
	}
	public static boolean deleteAccount(int accountID) throws SQLException {
		return AccountDAO.getAccountDAO().deleteAccount(accountID);
	}
}
