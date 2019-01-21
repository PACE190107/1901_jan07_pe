package revature.timjordan.projectZero.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import revature.timjordan.projectZero.base.OverDraftException;
import revature.timjordan.projectZero.models.Account;
import revature.timjordan.projectZero.models.User;
import revature.timjordan.projectZero.utils.JDBCConnectionUtil;

public class AccountDaoImplementation implements AccountDao {
		
	
	private static AccountDaoImplementation AccountDao;
	final static Logger Accountlog = Logger.getLogger(AccountDaoImplementation.class);
	
	private AccountDaoImplementation() {
		
	}
	
	
	public static AccountDaoImplementation getAccountDao() {
		if(AccountDao == null) {
			AccountDao = new AccountDaoImplementation();
		}
		
		return AccountDao;
	}
	
	
	@Override
	public boolean deposit(Account tempAccount, double amount) {
		//System.out.println("TempAccount: " + tempAccount);
		//System.out.println("Amount: " + amount);
		//System.out.println("Current Account Balance: $" + tempAccount.getAccountBalance());
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Withdraw Amount: $" + amount);
		double newBalance = tempAccount.getAccountBalance() + amount;
		System.out.println("New Account Balance: " + newBalance);
		System.out.println("------------------------------------------------------------------------------------");
		tempAccount.setAccountBalance(newBalance);
		
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "Call WITHDRAW_UPDATE(?,?)";
			CallableStatement ps= conn.prepareCall(sql);
			ps.setDouble(1, tempAccount.getAccountBalance());
			ps.setInt(2, tempAccount.getAccountID());
			
		 if(ps.executeUpdate() == 0) {
			return true;
		} else {
			throw new SQLException();
		}
	} catch(SQLException e) {
		Accountlog.error("error occured in catch block of create checking  dao implementation method");
		Accountlog.error(e.getMessage());
		Accountlog.error(e.getStackTrace());
		return false;
		
		
	}
		
		
	}
	
	
	@Override
	public boolean withdraw(Account tempAccount, double amount) {
		//System.out.println("tempAccount" + tempAccount);
		//System.out.println("Amount: " + amount);
		//System.out.println("Checking Account Balance: $" + tempAccount.getAccountBalance());
System.out.println("------------------------------------------------------------------------------------");		
		System.out.println("Withdraw Amount: $" + amount);
		double balance = tempAccount.getAccountBalance();
		if(amount <= balance) {
			double tempBalance = tempAccount.getAccountBalance() - amount;
			//System.out.println(tempBalance);
			tempAccount.setAccountBalance(tempBalance);
			System.out.println("New Account Balance: $" + tempAccount.getAccountBalance());
			System.out.println("------------------------------------------------------------------------------------");
			
			try(Connection conn = JDBCConnectionUtil.getConnection()) {
				String sql = "Call WITHDRAW_UPDATE(?,?)";
				CallableStatement ps= conn.prepareCall(sql);
				ps.setDouble(1, tempAccount.getAccountBalance());
				ps.setInt(2, tempAccount.getAccountID());
				
			 if(ps.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch(SQLException e) {
			Accountlog.error("error occured in catch block of create checking  dao implementation method");
			Accountlog.error(e.getMessage());
			Accountlog.error(e.getStackTrace());
			return false;
			
			
		}
			//Retrieve the curretn checking account info from database
		} else {
			//System.out.println("OverDraft/Insufficient funds!");
			try {
				throw new OverDraftException("OverDraft/Insufficient Funds");
			} catch(OverDraftException e) {
				Accountlog.error("Caught overdraftexception");
				return false;
			}
			
			
		}
		
		
		
	}
	
	
	@Override
	public boolean removeSavings(Account currentAccount) {
		System.out.println("In remove savings");
		System.out.println("currentAccount: " + currentAccount.getAccountID() + "Account type: " + currentAccount.getAccountType() );
		String type = currentAccount.getAccountType();
		int accountID = currentAccount.getAccountID();
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "DELETE  from accountstable where ACCOUNT_ID = '" + accountID + "' AND ACCOUNT_TYPE = '" + type + "'";
			Statement stmt = conn.createStatement();
			CallableStatement ps = conn.prepareCall(sql);
			
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
				
			}
		
		} 
		
		catch(SQLException e) {
			return false;
		}
		
		
		
		
	}
	
	
	
	@Override
	public boolean hasSavings(User currentUser) {
		
		//System.out.println("In account dao");
		int user_ID = currentUser.getId();
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from accountstable where USER_ID = '" + user_ID + "' AND ACCOUNT_TYPE = 'Savings'";
			Statement stmt = conn.createStatement();
			ResultSet results  = stmt.executeQuery(sql);
			
			if(results.next()) {
				//System.out.println("there is a savings account");
				return true;
			} else {
				//System.out.println("no savings account");
				return false;
			}
			
			
		
			
		} catch(SQLException e) {
			
		}
		
		
		
		
		return false;
	}
	
	
	@Override
	public List<Account> getAccounts(User currentUser, String accountType, int oneOrTwo) {
//		System.out.println(currentUser.getId());
//		System.out.println(accountType);
//		System.out.println(oneOrTwo);
//		System.out.println("in getaccounts");
		//get user id
		
		int user_ID = currentUser.getId();
		String accountTypes = accountType;
		//get accounts based on id
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			
			
			String sql = "select * from accountstable where USER_ID = '" + user_ID + "' AND ACCOUNT_TYPE = '" + accountTypes + "'";
			String sql2 = "select * from accountstable where USER_ID = '" + user_ID + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = null;
			if(oneOrTwo == 1) {
				results = stmt.executeQuery(sql);
			} else {
				results = stmt.executeQuery(sql2);
			}
			
			
			List<Account> userAccounts = new ArrayList<>();
			while(results.next()) {
				userAccounts.add(new Account(
						results.getInt("ACCOUNT_ID"),
						results.getInt("USER_ID"),
						results.getString("ACCOUNT_TYPE"),
						results.getDouble("ACCOUNT_BALANCE")
						));
				
			}
			return userAccounts;
		} catch(SQLException e) {
			
		}
		
		
		return new ArrayList<Account>();
	}
	
	
	@Override
	public boolean addSavings(User currentUser) {
		int user_ID = currentUser.getId();
		Account tempAccount = new Account(user_ID, "Savings", 0);
		
		try(Connection conn= JDBCConnectionUtil.getConnection()) {
			String sql = "Call create_account(?,?,?)";
			CallableStatement ps= conn.prepareCall(sql);
			
			
			ps.setInt(1, tempAccount.getUserID());
			ps.setString(2, tempAccount.getAccountType());
			ps.setDouble(3, tempAccount.getAccountBalance());
			
			if(ps.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch(SQLException e) {
			Accountlog.error("error occured in catch block of create checking  dao implementation method");
			Accountlog.error(e.getMessage());
			Accountlog.error(e.getStackTrace());
			
			
			
		}
		
		return false;
	}
	
	
	
	@Override
	public boolean addChecking(User currentUser) {
		System.out.println("In addChecking");
		System.out.println(currentUser.getUserName());
		String userName = currentUser.getUserName();
		User tempUser = null;
		//Get the user id from database
		
		
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select USER_ID from usertable where USER_NAME = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results  = stmt.executeQuery(sql);
			while(results.next()) {
				Accountlog.info("inside the while loop for getUser");
				tempUser =  new User(results.getInt("USER_ID"));
			}
			
			System.out.println(tempUser.getId());
			
			//System.out.println("USER" + tempUser);
			//System.out.println(tempUser.getPassword());
			
		} catch(SQLException e) {
			
		}
		//insert a new account into accounts table with user id and generate account id
		Account tempAccount = new Account(tempUser.getId(), "Checking", 0);
		try(Connection conn= JDBCConnectionUtil.getConnection()) {
			String sql = "Call create_account(?,?,?)";
			CallableStatement ps= conn.prepareCall(sql);
			
			
			ps.setInt(1, tempAccount.getUserID());
			ps.setString(2, tempAccount.getAccountType());
			ps.setDouble(3, tempAccount.getAccountBalance());
			
			if(ps.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch(SQLException e) {
			Accountlog.error("error occured in catch block of create checking  dao implementation method");
			Accountlog.error(e.getMessage());
			Accountlog.error(e.getStackTrace());
			
			
			
		}
		
		return false;
	}
	
	
	
}
