package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
import com.revature.utils.JDBCConnectUtil;

public class AccountDaoImplementation implements AccountDAO {

	private static AccountDaoImplementation accountDao;
	final static Logger log = Logger.getLogger(AccountDaoImplementation.class);
	
	private AccountDaoImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	public static AccountDaoImplementation getAccountDao() {
		
		if(accountDao == null)
				accountDao = new AccountDaoImplementation();
		
		return accountDao;
	}
	
	@Override
	public boolean insertAccount(Account account) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "insert into bank_accounts values(new_account_id.nextval,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getUserID());
			ps.setString(2, account.getAccountType());	
			ps.setDouble(3, account.getBalance());		
			
			
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
	public boolean deleteAccount(int accountID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from bank_accounts where account_id = ?";
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
	
	@Override
	public boolean updateBalance(int accountID, double amount) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update bank_accounts set balance = ? where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, accountID);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return true;				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccount(int accountID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from bank_accounts where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountID);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return new Account(results.getInt("account_id"), results.getInt("user_id"), results.getString("account_type") , results.getDouble("balance"));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Account();
	}

	@Override
	public List<Account> getAllAccounts(int userID) {

		try(Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from bank_accounts where user_id = " + userID);
			List<Account> allAccounts = new ArrayList<>();
			while(results.next()) {
				allAccounts.add(new Account(results.getInt("account_id"), results.getInt("user_id"), results.getString("account_type") , results.getDouble("balance")));
			}
			
			return allAccounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	
	@Override
	public boolean accountExists(int accountID, int userID) {
		try (Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from bank_accounts where user_id = "+ userID +" and account_id = "+ accountID);	
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public double getBalance(int accountID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from bank_accounts where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accountID);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return results.getDouble("balance");
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0.01;
	}

	@Override
	public boolean deleteAllUsersAccounts(int userID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from bank_accounts where user_id = ?";
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
	public boolean deleteAllAccounts() {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from bank_accounts";
			PreparedStatement ps = conn.prepareStatement(sql);	
			
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
