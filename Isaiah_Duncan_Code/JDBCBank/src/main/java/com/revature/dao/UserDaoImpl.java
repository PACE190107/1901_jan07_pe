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

import com.revature.models.User;
import com.revature.utils.JDBCConnectUtil;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl userDao;
	final static Logger log = Logger.getLogger(UserDaoImpl.class);
	private UserDaoImpl() {
	}
	
	public static UserDaoImpl getUserDao() {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}
	
	//used to register a user
	@Override
	public boolean insertUser(User user) {
		try (Connection conn = JDBCConnectUtil.getConnection()) {

			String sql = "INSERT INTO USERS (username, password, firstname, lastname) VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			
			if(ps.executeUpdate() == 1) {
				System.out.println("\nYou have successfully registered! You can log in now."); //test
				return true;
			} else {
				System.out.println("There is already a user with that username. Try a different username.");
				//throw new SQLException();
			}
			
		} catch (SQLException e) {
			System.out.println("There is already a user with that username. Try a different username.");
			//log.error("error occured in catch block of insert user dao implementation method");
			//log.error(e.getMessage());
			//log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public boolean insertUserProcedure(User user) {
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "call insert_customer(?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getPassWord());
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insertUserProcedure method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	//adds new user into the database
	@Override
	public boolean newUserProcedure(User user) {
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			String sql = "call register_user(?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassWord());
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			if(ps.executeUpdate() == 0) {
				System.out.println("\nNew account successfully registered!\n");
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of newUserProcedure method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		
		return false;
		
	}
	
	@Override
	public User getUser() {
		try (Connection conn = JDBCConnectUtil.getConnection()){
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			String sql = "SELECT * FROM users WHERE firstname = 'John'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				log.info("inside the while loop for getCustomer");
				return new User(
						results.getInt("user_id"), 
						results.getString("firstname"), 
						results.getString("lastname"),
						results.getString("username"),
						results.getString("password")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public boolean isUser(User user) {
		try (Connection conn = JDBCConnectUtil.getConnection()){

			String userName = user.getUserName();
			String sql = "SELECT username FROM users WHERE username = '"+userName+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			if(results.next()) {
				//System.out.println("Test : There is a record");
				return true;
			}else {
				//System.out.println("Test : There is NOT a record");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	//check if entered username and password is a match - login validation
	@Override
	public boolean checkUser(User user) {
		
		User quickUser = null;
		
		String userName = user.getUserName();
		String passWord = user.getPassWord();
		String comparedUsername = null;
		String comparedPassword = null;
		
		try (Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "SELECT username, password FROM users WHERE username = '"+userName+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				quickUser = new User(results.getString("username"),results.getString("password"));
				comparedUsername = quickUser.getUserName();
				comparedPassword = quickUser.getPassWord();
			}
			if(userName.equals(comparedUsername) && passWord.contentEquals(comparedPassword)) {
				System.out.println("The username and passowrd combo is a match!\n");
				return true;
			}else {
				//System.out.println("The username and/or passowrd you entered was not correct.\n");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public int getUserIdImpl(User user) {
		
		String storeUsername = user.getUserName();
		int userId = 0;
		
		try(Connection conn = JDBCConnectUtil.getConnection()){
			
			String sql = "SELECT user_id FROM users WHERE username='"+storeUsername+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				userId = results.getInt("user_id");
			}
			
		} catch (SQLException sqle) {
			
			log.error("error occured in catch block of getUserIdImpl method");
			log.error(sqle.getMessage());
			log.error(sqle.getStackTrace());
			
		}
		
		return userId;
	}
	
	//view all users (admin permission)
	@Override
	public List<User> getAllUsers() {
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM users";
			
			ResultSet results = stmt.executeQuery(sql);
			List<User> allUsers = new ArrayList<>();
			while (results.next()) {
				allUsers.add(new User(
						results.getInt("user_id"), 
						results.getString("username"), 
						results.getString("password"),
						results.getString("firstname"),
						results.getString("lastname"))
						);
			}
			return allUsers;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

	@Override
	public boolean editChangeUsernameImpl(User user) {
		
		int selectedUserId = user.getUserId();
		String selectedUsername = user.getUserName();
		
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE users SET username='"+selectedUsername+"' WHERE user_id="+ selectedUserId;
			stmt.executeUpdate(sql);
			System.out.println("You can successfully changed USER ID "+selectedUserId+" Username to " + selectedUsername);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	public boolean editChangeFirstnameImpl(User user) {
		int selectedUserId = user.getUserId();
		String selectedFirstname = user.getFirstName();
		
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE users SET firstname='"+selectedFirstname+"' WHERE user_id="+ selectedUserId;
			stmt.executeUpdate(sql);
			System.out.println("You can successfully changed USER ID "+selectedUserId+" First Name to " + selectedFirstname);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	public boolean editChangeLastnameImpl(User user) {
		int selectedUserId = user.getUserId();
		String selectedLastname = user.getLastName();
		
		try (Connection conn = JDBCConnectUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "UPDATE users SET lastname='"+selectedLastname+"' WHERE user_id="+ selectedUserId;
			stmt.executeUpdate(sql);
			System.out.println("You can successfully changed USER ID "+selectedUserId+" Last Name to " + selectedLastname);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public boolean deleteUserImpl(User user) {
		
		int selectedUserId = user.getUserId();
		
		try (Connection conn = JDBCConnectUtil.getConnection()) {

			String sql = "DELETE FROM users WHERE user_id= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, selectedUserId);
			
			if(ps.executeUpdate() == 1) {
				System.out.println("\nYou have successfully removed a user."); //test
				return true;
			} else {
				System.out.println("ERROR: User deletion failed. Something went wrong.");
				//throw new SQLException();
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR: Try-Catch block of deleteUserImpl method");
			//log.error("error occured in catch block of insert user dao implementation method");
			//log.error(e.getMessage());
			//log.error(e.getStackTrace());
		}
		return false;
	}


}
