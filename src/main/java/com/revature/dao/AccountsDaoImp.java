package com.revature.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Accounts;
import com.revature.utils.JDBCConnectionUtil;


public class AccountsDaoImp implements AccountsDao {
	
	private static AccountsDaoImp accountsDao;
	final static Logger log = Logger.getLogger(AccountsDaoImp.class);
	private AccountsDaoImp() {
	}
	
	public static AccountsDaoImp getAccountsDao() {
		if (accountsDao == null) {
			accountsDao = new AccountsDaoImp();
		}
		return accountsDao;
	}

	@Override
	public boolean insertAccounts(Accounts accounts) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into accounts_table values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accounts.getBank_account_balance());
			ps.setInt(2, accounts.getUser_id());
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	public boolean deleteAccountsProcedure(Accounts accounts) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call DELETE_ACCOUNT(?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, accounts.getBank_account_id());
			if(ps.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			   }
			} catch (SQLException e) {
				log.error("error occured in catch block of insert user dao implementation method");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		return false;
		}
	
	
	@Override
	public boolean updateAccountsProcedure(Accounts accounts) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call UPDATE_ACCOUNT(?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, accounts.getBank_account_id());
			ps.setInt(2, accounts.getBank_account_balance());
			if(ps.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	//stored procedure in SQL
	public boolean insertAccountsProcedure(Accounts accounts) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call ADD_ACCOUNT(?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, 0);
			ps.setInt(2, accounts.getUser_id());
			//ps.executeUpdate();
			if(ps.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	public Accounts getAccounts() {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
		    String sql = "select * from accounts_table where user_id = '31'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				log.info("inside the while loop for getUser");
				return new Accounts(
						results.getInt("BANK_ACCOUNT_ID"), 
						results.getInt("USER_ID")  
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public List<Accounts> getAllAccounts() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from accounts_table";
			
			log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			log.info("viewing the results");
			List<Accounts> allAccounts = new ArrayList<>();
			while (results.next()) {
				allAccounts.add(new Accounts(
						results.getInt("BANK_ACCOUNT_ID"), 
						results.getInt("ACCOUNT_BALANCE"),
						results.getInt("USER_ID"))
						);
			}
			return allAccounts;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}
	
}
