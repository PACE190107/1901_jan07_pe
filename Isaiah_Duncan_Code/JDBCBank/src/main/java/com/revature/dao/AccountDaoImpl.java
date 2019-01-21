package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.utils.JDBCConnectUtil;

public class AccountDaoImpl implements AccountDao {

	private static AccountDaoImpl accountDao;
	final static Logger log = Logger.getLogger(AccountDaoImpl.class);
	private AccountDaoImpl() {
	}
	
	public static AccountDaoImpl getAccountDao() {
		if (accountDao == null) {
			accountDao = new AccountDaoImpl();
		}
		return accountDao;
	}

	public boolean addNewChecking(User user) {
		User quickUser = null;
		String userName = user.getUserName();
		
		//get the user_id from the users table to create a new record into the child table
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "SELECT user_id FROM users WHERE username='"+userName+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//System.out.println("addNewChecking test: the acquired user ID is: " + results.getInt("user_id"));
				quickUser = new User(results.getInt("user_id"));
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
				
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "INSERT INTO accounts (account_id, account_bal, account_type) values ("+quickUser.getUserId()+", 0.00, 'Checking')";
			Statement stmt = conn.createStatement();
			//stmt.executeUpdate(sql);
			if(stmt.executeUpdate(sql) == 1) {
				System.out.println("Notice: You have successfully created a new checking account!"); //test
				return true;
			} else {
				System.out.println("ERROR: addNewChecking method: You've failed to add an account.");
				//throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("ERROR: addNewChecking method is having issues.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());

		}
		
		return false;
	}

	public List<Account> displayUserAccountsImpl(User user) {
		
		User quickUser = null;
		String userName = user.getUserName();
		
		//get the user_id from the users table to create a new record into the child table
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username='"+userName+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//System.out.println("addNewChecking test: the acquired user ID is: " + results.getInt("user_id"));
				quickUser = new User(
						results.getInt("user_id"),
						results.getString("username"),
						results.getString("password"),
						results.getString("firstname"),
						results.getString("lastname"));
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		
		
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM accounts WHERE account_id='"+quickUser.getUserId()+"'";
			ResultSet results = stmt.executeQuery(sql);
	
			List<Account> allAccounts = new ArrayList<>();
			
			while (results.next()) {
				allAccounts.add(new Account(
						results.getInt("account_id"), 
						results.getString("account_num"), 
						results.getString("account_type"),
						results.getDouble("account_bal"))
						);
			}
			
			return allAccounts;
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
			return new ArrayList<>();
		}
	
	//method to withdraw from account
	public boolean withdrawFromAccountImpl(Account acct) {
		
		//User quickUser = null;
		//int accountId = acct.getAccountID();
		String selectedAcctNum = acct.getAccountNum();
		double withdrawAmount = acct.getAccountBal();
		withdrawAmount = Math.round(withdrawAmount * 100.0) / 100.0;
		
		String accountType = null;
		double currentAcctBal = 0;
		double calculatedBal = 0;
		
		//get the user_id from the users table to create a new record into the child table
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "SELECT account_bal, account_type FROM accounts WHERE account_num='"+selectedAcctNum+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//System.out.println("addNewChecking test: the acquired user ID is: " + results.getInt("user_id"));
				currentAcctBal = results.getDouble("account_bal");
				currentAcctBal = Math.round(currentAcctBal * 100.0) / 100.0;
				accountType = results.getString("account_type");
			}
		} catch (SQLException e) {
			log.error("Error Code 1: occured in catch block of withdraw method.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		
		try(Connection conn = JDBCConnectUtil.getConnection()){
			
			if(withdrawAmount > currentAcctBal) {
				System.out.println("If withdraw amount (" + withdrawAmount + ") is greater than current balance ("+currentAcctBal+")");
				return false;
				
			} else {
				
				calculatedBal = currentAcctBal - withdrawAmount;
				String sql = "UPDATE accounts SET account_bal="+calculatedBal+" WHERE account_num="+selectedAcctNum+"";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("You have successfully withdrawn " + withdrawAmount + " USD from your " + accountType + " Account.\n");
				System.out.println("Your new "+accountType+" Account balance is " + calculatedBal);
				return true;
				
			}
			
			
			
		} catch (SQLException e) {
			
			log.error("Error Code 2: occured in catch block of withdraw method.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			
		}
		
		return false;
		
	}

	//method to deposit to account
	public boolean depositFromAccountImpl(Account acct) {
		
		//User quickUser = null;
		//int accountId = acct.getAccountID();
		
		String selectedAcctNum = acct.getAccountNum();
		double depositAmount = acct.getAccountBal();
		depositAmount = Math.round(depositAmount * 100.0) / 100.0;
		
		String accountType = null;
		double currentAcctBal = 0;
		double calculatedBal = 0;
		
		//get the user_id from the users table to create a new record into the child table
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "SELECT account_bal, account_type FROM accounts WHERE account_num='"+selectedAcctNum+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//System.out.println("addNewChecking test: the acquired user ID is: " + results.getInt("user_id"));
				currentAcctBal = results.getDouble("account_bal");
				currentAcctBal = Math.round(currentAcctBal * 100.0) / 100.0;
				accountType = results.getString("account_type");
			}
		} catch (SQLException e) {
			log.error("Error Code 1: occured in catch block of withdraw method.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		
		try(Connection conn = JDBCConnectUtil.getConnection()){

				calculatedBal = currentAcctBal + depositAmount;
				String sql = "UPDATE accounts SET account_bal="+calculatedBal+" WHERE account_num="+selectedAcctNum+"";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("You have successfully deposited " + depositAmount + " USD to your " + accountType + " Account.\n");
				System.out.println("Your new "+accountType+" Account balance is " + calculatedBal);
				return true;

		} catch (SQLException e) {
			
			log.error("Error Code 2: occured in catch block of withdraw method.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			
		}
		
		return false;
		
	}
	
	public boolean addCheckingAcctImpl(Account acct) {
		
		int userId = acct.getAccountID();
		
		try(Connection conn = JDBCConnectUtil.getConnection()){
			
			String sql = "INSERT INTO accounts (account_id, account_bal, account_type) VALUES ("+userId+", 0.00, 'Checking')";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			return true;
			
		} catch (SQLException sqle) {
			
			System.out.println("ERROR: found in try-catch block of the addCheckingAcctImpl method!");
			log.error(sqle.getMessage());
			log.error(sqle.getStackTrace());
			
		}
		
		return false;

	}
	
	public boolean addSavingsAcctImpl(Account acct) {
		
		int userId = acct.getAccountID();
		
		try(Connection conn = JDBCConnectUtil.getConnection()){
			
			String sql = "INSERT INTO accounts (account_id, account_bal, account_type) VALUES ("+userId+", 0.00, 'Savings')";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			return true;
			
		} catch (SQLException sqle) {
			
			System.out.println("ERROR: found in try-catch block of the addSavingsAcctImpl method!");
			log.error(sqle.getMessage());
			log.error(sqle.getStackTrace());
		}
		
		return false;

	}

	public boolean removeAccountImpl(Account acct) {

		//int userId = acct.getAccountID();
		String accountNum = acct.getAccountNum();
		double currentBal = 1;
		
		try (Connection conn = JDBCConnectUtil.getConnection()){
			
			String sql = "SELECT account_bal FROM accounts WHERE account_num='"+accountNum+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next()) {
				currentBal = results.getDouble("account_bal");
			}
			
		} catch (SQLException sqle) {
			
			log.error("Error Code: occured in catch block of remove account method.");
			log.error(sqle.getMessage());
			log.error(sqle.getStackTrace());
			
		}
		
		try(Connection conn = JDBCConnectUtil.getConnection()){
			
			if (currentBal == 0) {
				
				String sql = "DELETE FROM accounts WHERE account_num='"+accountNum+"'";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				return true;
				
			} else if(currentBal > 0) {
				
				System.out.println("UH OH!: The account you tried to remove still has funds in it. Withdraw all of your funds to remove this account.");
				return false;
				
			} else if(currentBal < 0) {
				
				System.out.println("UH OH!: Looks like you have an overdraft on your account! Be sure to pay the balance you owe before attempting to remove this account.");
				return false;
				
			} 
			
		} catch (SQLException sqle) {
			
			log.error("Error Code2: occured in second catch block of remove account method.");
			log.error(sqle.getMessage());
			log.error(sqle.getStackTrace());
			
		}
		
		return false;
	}
	
}
