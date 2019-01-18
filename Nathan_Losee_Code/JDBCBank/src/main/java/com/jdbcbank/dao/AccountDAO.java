package com.jdbcbank.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.jdbcbank.models.Account;
import com.jdbcbank.models.AccountType;
import com.jdbcbank.util.BankErrors;
import com.jdbcbank.util.ConnectionManager;

public class AccountDAO implements AccountDAOable {
	private static PreparedStatement findAccounts;
	private static AccountDAO accountDAO;
	
	private AccountDAO() { }
	
	public static AccountDAO getAccountDAO() throws SQLException {
		if (accountDAO == null) {
			accountDAO = new AccountDAO();
			initializeStatements();
		}
		return accountDAO;
	}
	
	private static void initializeStatements() throws SQLException {
		findAccounts = ConnectionManager.getJDBCConnection().prepareStatement(
			"SELECT * FROM bank_accounts " +
				"WHERE user_id = ?");
	}
	
	@Override
	public boolean createAccount(int userID, String type, Double amount) throws SQLException {
		boolean isValidType = false;
		for (AccountType aType : AccountType.values())
			if (type.toLowerCase().equals(aType.toString()))
				isValidType = true;
		if (!isValidType)
			throw new BankErrors.InvalidTypeException();
		
		if (amount <= 0)
			throw new BankErrors.InvalidAmountException();
		
		CallableStatement stmnt = ConnectionManager.getJDBCConnection().prepareCall("CALL insert_account(?,?,?,?)");
		stmnt.setInt(1, userID);
		stmnt.setString(2, AccountType.valueOf(type).toString());
		stmnt.setDouble(3, amount);
		stmnt.registerOutParameter(4, Types.INTEGER);
		stmnt.executeUpdate();
		
		if (stmnt.getInt(4) > 0) {
			TransactionDAO.getTransactionDAO().createTransaction(stmnt.getInt(4), "_DEPOSIT", amount, amount);
			stmnt.close();
			return true;
		}
		else
			throw new BankErrors.InvalidUsernamePasswordException();
	}
	
	@Override
	public List<Account> readAccounts(int userID) throws SQLException {
		findAccounts.setInt(1, userID);
		ResultSet foundAccounts = findAccounts.executeQuery();

		List<Account> accounts = new ArrayList<Account>();
		while (foundAccounts.next()) {
			Account account = new Account();
			
			account.setAccountID(foundAccounts.getInt(1));
			switch (foundAccounts.getString(3)) {
			case "checking":
				account.setType(AccountType.checking);
				break;
			case "savings":
				account.setType(AccountType.savings);
				break;
			}
			account.setBalance(foundAccounts.getDouble(4));

			accounts.add(account);
		}
		
		return accounts;
	}
	@Override
	public Account readAccount(int accountID) throws SQLException {
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		ResultSet foundAccount = stmnt.executeQuery(
			"SELECT * FROM bank_accounts " +
				"WHERE account_id = " + accountID);
		
		if (foundAccount.next()) {
			Account account = new Account();

			account.setAccountID(foundAccount.getInt(1));
			switch (foundAccount.getString(3)) {
			case "checking":
				account.setType(AccountType.checking);
				break;
			case "savings":
				account.setType(AccountType.savings);
				break;
			}
			account.setBalance(foundAccount.getDouble(4));
			
			stmnt.close();
			
			return account;
		} else {
			throw new BankErrors.InvalidAccountIDException();
		}
	}

	@Override
	public boolean updateAccount(int accountID, double amount, boolean isDeposit) throws SQLException {
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		Account account = readAccount(accountID);
		if (!isDeposit && amount > account.getBalance())
			throw new BankErrors.OverdraftException();
		
		if (amount <= 0)
			throw new BankErrors.InvalidAmountException();
		
		int updatedAccounts = stmnt.executeUpdate(
			"UPDATE bank_accounts " +
				"SET account_balance = " + (account.getBalance() + (isDeposit ? amount : -amount)) + " " +
				"WHERE account_id = " + accountID);
		
		stmnt.close();
		
		if (updatedAccounts > 0) {
			TransactionDAO.getTransactionDAO().createTransaction(accountID, (amount > 0 ? "deposit" : "withdraw"),
				amount, account.getBalance() + (isDeposit ? amount : -amount));
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAccounts(int userID) throws SQLException {
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		ResultSet rs = stmnt.executeQuery(
			"SELECT * FROM bank_accounts " +
					"WHERE user_id = " + userID);
		
		int numAccounts = 0;
		
		while (rs.next()) {
			if (readAccount(rs.getInt(1)).getBalance() > 0.00)
				throw new BankErrors.NonEmptyAccountException();
			TransactionDAO.getTransactionDAO().deleteTransactions(rs.getInt(1));
			numAccounts++;
		}
		
		int deletedAccounts = stmnt.executeUpdate(
			"DELETE FROM bank_accounts " +
				"WHERE user_id = " + userID);
		
		stmnt.close();
		
		if (deletedAccounts >= numAccounts)
			return true;
		else
			throw new BankErrors.InvalidAccountIDException();
	}
	@Override
	public boolean deleteAccount(int accountID) throws SQLException {
		if (readAccount(accountID).getBalance() > 0.00)
			throw new BankErrors.NonEmptyAccountException();
		
		TransactionDAO.getTransactionDAO().deleteTransactions(accountID);
		
		Statement stmnt = ConnectionManager.getJDBCConnection().createStatement();
		int deletedAccounts = stmnt.executeUpdate(
			"DELETE FROM bank_accounts " +
				"WHERE account_id = " + accountID);
		
		stmnt.close();
		
		if (deletedAccounts > 0)
			return true;
		else
			throw new BankErrors.InvalidAccountIDException();
	}
}
