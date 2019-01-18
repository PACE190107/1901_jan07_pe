package com.jdbcbank.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import com.jdbcbank.models.Transaction;
import com.jdbcbank.services.AccountServices;
import com.jdbcbank.util.ConnectionManager;

public class TransactionDAO implements TransactionDAOable {
	private static PreparedStatement findTransactions;
	private static TransactionDAO transactionDAO;
	
	private TransactionDAO() { }
	
	public static TransactionDAO getTransactionDAO() throws SQLException {
		if (transactionDAO == null) {
			transactionDAO = new TransactionDAO();
			initializeStatements();
		}
		return transactionDAO;
	}
	
	private static void initializeStatements() throws SQLException {
		findTransactions = ConnectionManager.getJDBCConnection().prepareStatement(
			"SELECT * FROM Transactions " +
				"WHERE account_id = ?");
	}
	
	@Override
	public boolean createTransaction(int accountID, String action, double amount, double balance) throws SQLException {
		AccountServices.readAccount(accountID);
		
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();

		int createdTransactions = stmnt.executeUpdate(
			"INSERT INTO transactions " +
				"VALUES (to_timestamp('" + (Timestamp.valueOf(LocalDateTime.now())) + "','YYYY-MM-DD HH24:MI:SS.FF')," +
				accountID + "," +
				(amount > 0 ? "'DEPOSIT'" : "'WITHDRAW'") + "," +
				amount + "," +
				balance + ")");
		stmnt.close();
		
		if (createdTransactions > 0)
			return true;
		return false;
	}

	@Override
	public Map<Timestamp, Transaction> readTransactions(int accountID) throws SQLException {
		AccountServices.readAccount(accountID);
		
		Map<Timestamp, Transaction> transactions = new TreeMap<Timestamp, Transaction>();
		findTransactions.setInt(1, accountID);
		ResultSet foundTransactions = findTransactions.executeQuery();
		
		while (foundTransactions.next()) {
			Transaction transaction = new Transaction();
			
			transaction.setAction(foundTransactions.getString(3).toUpperCase());
			transaction.setAmount(foundTransactions.getDouble(4));
			transaction.setBalance(foundTransactions.getDouble(5));
			
			transactions.put(foundTransactions.getTimestamp(1), transaction);
		}
		
		return transactions;
	}

	@Override
	public boolean deleteTransactions(int accountID) throws SQLException {
		AccountServices.readAccount(accountID);
		
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		
		ResultSet rs = stmnt.executeQuery(
				"SELECT * FROM transactions " +
						"WHERE account_id = " + accountID);
			
		int numTransactions = 0;
		
		while (rs.next())
			numTransactions++;
			
		int deletedTransactions = stmnt.executeUpdate(
			"DELETE FROM transactions " +
				"WHERE account_id = " + accountID);
		stmnt.close();
		
		if (deletedTransactions >= numTransactions)
			return true;
		return false;
	}
}
