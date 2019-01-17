package com.jdbcbank.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import com.jdbcbank.dao.TransactionDAO;
import com.jdbcbank.models.Transaction;

public class TransactionServices {
	public static boolean createTransaction(int accountID, String action, double amount, double balance) throws SQLException {
		return TransactionDAO.getTransactionDAO().createTransaction(accountID, action, amount, balance);
	}
	public static Map<Timestamp, Transaction> readTransactions(int accountID) throws SQLException {
		return TransactionDAO.getTransactionDAO().readTransactions(accountID);
	}
	public static boolean deleteTransactions(int accountID) throws SQLException {
		return TransactionDAO.getTransactionDAO().deleteTransactions(accountID);
	}
}
