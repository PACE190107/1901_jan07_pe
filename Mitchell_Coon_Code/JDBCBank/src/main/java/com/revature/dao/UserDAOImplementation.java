package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.JDBCConnectionUtil;

//Dao class implements DAO interface
//DAO class inserts/retrieves data from database
//In our example connection to database will be handled by utils

public class UserDAOImplementation implements UserDAO {

	private static UserDAOImplementation userDAO;
	final static Logger log = Logger.getLogger(UserDAOImplementation.class);
	private UserDAOImplementation() {
	}
	
	public static UserDAOImplementation getUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAOImplementation();
		}
		return userDAO;
	}

	@Override
	public boolean insertUserProcedure(User user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call insert_user(?,?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.registerOutParameter(5, Types.NUMERIC);
			ps.executeUpdate();
			if(ps.getInt(5) > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Bank account creation failed");
		}
		return false;
	}

	@Override
	public boolean deposit(double amount, int userID, String accountType) {
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "update Accounts set balance = "+ amount +" + balance where user_id = "+ userID +" and account_type = '"+ accountType +"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Deposit failed");
		}
		return false;
	}
	
	@Override
	public boolean withdraw(double amount, int userID, String accountType) {
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "update Accounts set balance = -"+ amount +" + balance where user_id = "+ userID +" and account_type = '"+ accountType +"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//ps.setDouble(1, amount);
			//ps.setInt(2, userID);
			//ps.setString(3, accountType);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Withdraw failed");
		}
		return false;
	}
	
	@Override
	public void viewBalances(int user_Id) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "select * from Accounts where User_ID = " + user_Id;
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				System.out.println("Account: " + results.getString("Account_Type") + "; balance: " + results.getDouble("Balance"));
			}
			System.out.println("");
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not view balances");
		} 
	}
	
	@Override
	public void viewTransactions(int account_Id) {
		
	}
	
	@Override
	public boolean deleteAccount(int user_Id, String account_type) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "delete from Accounts where Accounts.User_ID = " + user_Id + " and Accounts.account_type = '" + account_type + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Account deletion failed");
		}
		return false;

	}
	
	@Override
	public boolean closeAllAccounts(int user_Id) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "delete from BankUsers where BankUsers.User_ID = " + user_Id;
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Bank account closing failed");
		}
		return false;
	}
	
	@Override
	public User getUser(String userName) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			// bad practice; never hard code values in a method
			// how to avoid using prepared statements - PS supports parameterized SQL
						
			//select * from customer where C_FIRSTNAME is like 'P%'
			String sql = "select * from BankUsers where UserName = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				return new User(
						results.getInt("User_ID"),
						results.getString("Firstname"), 
						results.getString("Lastname"),
						results.getString("Username"),
						results.getString("Pass")
						);
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve user information");
		} 
		return null;
	}

	@Override
	public boolean verifyUser(String username, String pass) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select count(pass) from BankUsers where BankUsers.username = '"+ username +"' and BankUsers.pass = get_User_Hash('"+ username +"', '"+ pass +"')";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				if(results.getInt("Count(Pass)") == 1) {
					return true;
				} else {
					throw new SQLException();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("User verification failed");
		}
		return false;
	}
	
	@Override
	public int retrieveID(String userName) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from BankUsers where UserName = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				return results.getInt("User_ID");
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve user ID");
		} 
		return 0;
	}

	@Override
	public double checkBalance(int user_Id, String accountType) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from Accounts where User_ID = " + user_Id + " and account_Type = '" + accountType + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				return results.getDouble("Balance");
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve account balance information");
		}
		return 0;
	}
	
	@Override
	public boolean checkBalanceEmpty(int userId, String account_type) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "select * from Accounts where User_ID = " + userId + " and account_type = '" + account_type + "'";
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				if(results.getDouble("Balance") != 0){
					return false;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve account balance information");
		} 
		return true;
	}

	@Override
	public boolean checkBalancesEmpty(int userId) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "select * from Accounts where User_ID = " + userId;
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				if(results.getDouble("Balance") != 0){
					return false;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve account balance information");
		} 
		return true;
	}

	@Override
	public boolean addAccount(int user_Id, String account_type) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "insert into Accounts values(null, " + user_Id + ", '"+ account_type + "', 0)";
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Account addition failed");
		}
		return false;
	}

	@Override
	public boolean accountExists(int userId, String account_type) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select count(user_ID) from Accounts where Accounts.user_ID = "+ userId +" and Accounts.account_type = '" + account_type + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				if(results.getInt("Count(user_ID)") == 1) {
					return true;
				}
				else if (results.getInt("Count(user_ID)") == 0) {
					return false;
				}
				else {
					throw new SQLException();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve account information");
		}
		return false;
	}

	@Override
	public void viewUsers() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "select * from BankUsers";
			ResultSet results = stmt.executeQuery(sql);
			System.out.println("Displaying users: \n");
			while (results.next()) {
				System.out.println("Name: " + results.getString("firstname") + " " + results.getString("lastname"));
				System.out.println("User ID: " + results.getInt("User_ID"));
				System.out.println("Username: " + results.getString("username"));
				System.out.println("\n");
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve user information");
		} 
	}

	@Override
	public boolean userExists(int user_id) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select count(user_ID) from BankUsers where BankUsers.user_ID = "+ user_id;
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next()) {
				if(results.getInt("Count(user_ID)") == 1) {
					return true;
				}
				else if (results.getInt("Count(user_ID)") == 0) {
					return false;
				}
				else {
					throw new SQLException();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("Could not retrieve user information");
		}
		return false;
	}

	@Override
	public boolean updateUser(int user_id, User updatedUser) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "update BankUsers set firstname = '" + updatedUser.getFirstName()
			+ "', lastname = '" + updatedUser.getLastName() + "', username = '" + updatedUser.getUserName()
			+ "', pass = get_User_Hash('" + updatedUser.getFirstName() + "', '" + updatedUser.getLastName()
			+ "') where user_id = " + user_id;
			PreparedStatement ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("SQL exception occurred");
			System.out.println(e.getMessage());
			System.out.println("User update failed");
		}
		return false;
	}
}
