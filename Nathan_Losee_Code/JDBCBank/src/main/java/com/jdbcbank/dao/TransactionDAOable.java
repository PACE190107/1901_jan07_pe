package com.jdbcbank.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import com.jdbcbank.models.Transaction;

public interface TransactionDAOable {
	public boolean createTransaction(int accountID, String action, double amount, double balance) throws SQLException;
	public Map<Timestamp, Transaction> readTransactions(int accountID) throws SQLException;
	public boolean deleteTransactions(int accountID) throws SQLException;
}
