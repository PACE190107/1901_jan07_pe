package com.revature.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.ConnectionUtil;

public class UserDAOImp implements UserDAO{
	//Private class object
	private static UserDAOImp userDAO;
	//Logger initialization
	final static Logger log = Logger.getLogger(UserDAOImp.class);
	
	//Creates an Instance of the class
	public static UserDAOImp getUserDao() {
		if (userDAO == null) {
			userDAO = new UserDAOImp();
		}
		return userDAO;
	}

	
//CREATING AND DELETING--------------------------

	@Override
	public boolean createUser(User user) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement cStmt = conn.prepareCall("CALL INSERT_USER(?,?,?,?)");
			cStmt.setString(1,user.getFirstName());
			cStmt.setString(2,user.getLastName());
			cStmt.setString(3,user.getUserName());
			cStmt.setString(4,user.getPassword());
			
			if(cStmt.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (Exception e) {
			log.error("Error: Cannot create user!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
	}

	
	@Override
	public boolean deleteUser(User user) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement cStmt = conn.prepareCall("call DELETE_USER(?)");
			cStmt.setInt(1, user.getUser_id());
			
			if(cStmt.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (Exception e) {
			log.error("Error: Cannot delete user!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
	}
	
	
	@Override
	public boolean createAccount(User user, Account acct) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement cStmt = conn.prepareCall("CALL INSERT_ACCOUNT(?,?,?)");
			cStmt.setInt(1,user.getUser_id());
			cStmt.setString(2, acct.getAccountType());
			cStmt.setDouble(3, acct.getAccountBalance());
			
			if(cStmt.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (Exception e) {
			log.error("Error: Cannot create account!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
	}
	
	
	@Override
	public boolean deleteAccount(int acct_id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement cStmt = conn.prepareCall("call DELETE_ACCOUNT(?)");
			cStmt.setInt(1,acct_id);
			if(cStmt.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (Exception e) {
			log.error("Error: Cannot delete user!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
	}



//WITHDRAWAL AND DEPOSIT--------------------------
	@Override
	public boolean transfer(double amount, int acc_ID) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("call TRANSFER(?,?)");
			stmt.setDouble(1, amount);
			stmt.setInt(2, acc_ID);
			if (stmt.executeUpdate() == 0) {
				return true;
			}
		}catch (Exception e) {
				log.error("Error: Cannot retrieve update balance!");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
				return false;
			}
		return false;
	}
	
//GETTING DATA-------------------------------------
	
	public int getID(User user) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{? = call GETID(?)}");
			stmt.registerOutParameter(1, Types.NUMERIC);
			stmt.setString(2,user.getUserName());
			stmt.execute();
			return stmt.getInt(1);
			
		} catch (Exception e) {
			log.error("Error: Cannot retrieve user ID!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return 0;
		}
	}
	
	public List<Account> getAllAccounts(int user_id){
		try (Connection conn = ConnectionUtil.getConnection()){
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM user_accounts WHERE u_id = "+user_id;
			ResultSet results = stmt.executeQuery(query);
			List<Account> all_accounts = new ArrayList<>();
			while(results.next()) {
				all_accounts.add(new Account(
						results.getString("ACCT_TYPE"), 
						results.getInt("ACCOUNT_ID"), 
						results.getDouble("ACCT_BALANCE"))
						);
			}

			return all_accounts;
			
		} catch (Exception e) {
			log.error("Error: Cannot retrieve Accounts!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return new ArrayList<Account>();
		}
	}
    
//CONFIRMING EXISTING DATA------------------------------------	
	
	public boolean findID(String username) {
		try (Connection conn = ConnectionUtil.getConnection()){
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM user_details WHERE USERNAME = '"+username +"'";
			ResultSet results = stmt.executeQuery(query);
			if (results.next() == true) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			log.error("Error: Cannot get user ID");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
    }

	public String findPass(String username) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String PASS = "";
			Statement stmt = conn.createStatement();
			String query = "SELECT PASS FROM user_details WHERE USERNAME = '"+username +"'";
			ResultSet results = stmt.executeQuery(query);
			if (results.next()) {
				PASS =  results.getString("PASS");
			}
			return PASS;

		} catch (Exception e) {
			log.error("Error: Cannot find password.");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return null;
		}
    }
	
	public boolean confirmPass(User user) {
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{? = call GET_USER_HASH(?,?)}");
			stmt.registerOutParameter(1, Types.NVARCHAR);
			stmt.setString(2, user.getUserName());
			stmt.setString(3,user.getPassword());
			stmt.execute();
			
			if(stmt.getString(1).equals(UserService.getUserService().findPass(user))) {
				return true;
			}
			
		} catch (Exception e) {
			log.error("Error: Cannot retrieve user password!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return false;
	}


//SUPER USER:-----------------------------------

	public User getSuperUser() {
		try {
		final BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\super.properties"));
		String username = bf.readLine();
		String password = bf.readLine();
		User superUser = new User(username, password);
		bf.close();
		return superUser;
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file: super.properties.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot read file: super.properties.");
			e.printStackTrace();
		}
		return new User();
	}

	public List<User> getAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()){
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM USER_DETAILS";
			ResultSet results = stmt.executeQuery(query);
			List<User> all_users = new ArrayList<User>();
			while(results.next()) {
				all_users.add(new User(results.getInt("USER_ID"),results.getString("FIRSTNAME"), results.getString("LASTNAME"),
						results.getString("USERNAME"),
						results.getString("PASS")
						));
			}
			return all_users;
		} catch (Exception e) {
			log.error("Error: Cannot retrieve User details!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return new ArrayList<User>();
		}
}

	public boolean modifyUser(User user, int command) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "";
			switch (command) {
			case 1:
				query = "update USER_DETAILS set FIRSTNAME = '"+user.getFirstName() +"' WHERE USER_ID = " +user.getUser_id();
				System.out.println(query);
				break;
			case 2:
				query = "update USER_DETAILS set LASTNAME = '"+user.getLastName() +"' WHERE USER_ID = " +user.getUser_id();
				break;
			case 3:
				query = "update USER_DETAILS set USERNAME = '"+user.getUserName() +"' WHERE USER_ID = " +user.getUser_id();
				break;
			}
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.execute();
			 if (stmt.executeUpdate() > 0) {
				return true;
			}
			 else {
				 throw new SQLException();
			 }
		} catch (Exception e) {
			log.error("Error: Cannot change parameter!");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
}