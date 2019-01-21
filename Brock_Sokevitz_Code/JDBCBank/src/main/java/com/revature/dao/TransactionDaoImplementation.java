package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Transaction;
import com.revature.utils.JDBCConnectUtil;

public class TransactionDaoImplementation implements TransactionDAO{
	private static TransactionDaoImplementation transactionDao;
	final static Logger log = Logger.getLogger(TransactionDaoImplementation.class);
	
	private TransactionDaoImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionDaoImplementation getTransactionDao() {
		
		if(transactionDao == null)
			transactionDao = new TransactionDaoImplementation();
		
		return transactionDao;
	}
	@Override
	public boolean insertTransaction(Transaction transaction) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "insert into account_transactions values(new_transaction_id.nextval,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction.getAccountID());
			ps.setInt(2, transaction.getUserID());	
			ps.setDouble(3, transaction.getAmount());		
			
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public List<Transaction> getAllTransactions(int userID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from account_transactions where user_id = " + userID);
			List<Transaction> allTransactions = new ArrayList<>();
			while(results.next()) {
				allTransactions.add(new Transaction(results.getInt("transaction_id"), results.getInt("account_id"), results.getInt("user_id"), results.getDouble("amount")));
			}
			
			return allTransactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public boolean deleteAllUserTransactions(int userID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from account_transactions where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, userID);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	public boolean deleteAllAccountTransactions(int accountID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from account_transactions where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, accountID);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

}
