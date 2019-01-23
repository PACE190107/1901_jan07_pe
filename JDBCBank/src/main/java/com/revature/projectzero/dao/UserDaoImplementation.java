package com.revature.projectzero.dao;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.log4j.Logger;

import com.revature.projectzero.driver.Driver;
import com.revature.projectzero.models.RegUser;
import com.revature.projectzero.models.User;
import com.revature.projectzero.util.*;

public class UserDaoImplementation implements UserDao {
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	Scanner scanner = new Scanner(System.in);
	NumberFormat formatter = NumberFormat.getCurrencyInstance();

	@Override
	public boolean insertCustomerProcedure(User user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into Customer values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getPassword());
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	public RegUser getRegUser() {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String[] userInfo = login();
			String sql = "call RETURN_BANK_CUST_HASH(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, userInfo[0]);
			cs.setString(2, userInfo[1]);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			String hashedpwd = cs.getString(3);
			sql = "select * from BANK_CUST where CUST_USERNAME = '" +
				userInfo[0] + "' AND CUST_PASSWORD =  '" + hashedpwd + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			if (results.next() == false) {
				System.out.println("Login failed.  No matching username/password.");
			} else {
				do {
					return new RegUser(
							results.getInt("CUST_ID"), 
							results.getString("CUST_FIRSTNAME"), 
							results.getString("CUST_LASTNAME"),
							results.getString("CUST_USERNAME"),
							results.getString("CUST_PASSWORD")
						);
					} while (results.next());
				}
			} catch (SQLException e) {
		}
		return null;
	}

	
	public String[] login() {
		String userName = "";
		String password = "";
		System.out.print("Enter your username: ");
		try {
			userName = scanner.nextLine();
			System.out.print("Enter your password: ");
			password = scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("login failed.");
		}
		String[] userInfo = {userName, password};
		return userInfo;
	}
	
	
	public RegUser getNewUser() {
		String [] newUserInfo = login();
		System.out.print("Enter your first name: ");
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String firstName = scanner.nextLine();
			System.out.print("Enter your last name: ");
			String lastName = scanner.nextLine();
			String sql = "call RETURN_BANK_CUST_HASH(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, newUserInfo[0]);
			cs.setString(2, newUserInfo[1]);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			String hashedpwd = cs.getString(3);			
			sql = "call INSERT_BANK_CUST(?,?,?,?)";
			cs = conn.prepareCall(sql);
			cs.setString(1, firstName);
			cs.setString(2, lastName);
			cs.setString(3, newUserInfo[0]);
			cs.setString(4, hashedpwd);
			cs.executeUpdate();
			sql = "select * from BANK_CUST where CUST_USERNAME = '" +
				newUserInfo[0] + "' AND CUST_PASSWORD =  '" + hashedpwd + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()){
				return new RegUser(
						results.getInt("CUST_ID"), 
						results.getString("CUST_FIRSTNAME"), 
						results.getString("CUST_LASTNAME"),
						results.getString("CUST_USERNAME"),
						results.getString("CUST_PASSWORD")
					);
				}
			} catch (SQLException e) {
			} catch (Exception e) {
				System.out.println("login failed.");
			}	
		return null;		
	}

	public boolean getSuperUser() {
		String superUserFilepath = "C:\\Workspace\\JDBCBank\\superuserinfo.txt";
		StringBuilder getSuperUserInfo = new StringBuilder();
		try(Stream<String> stream = Files.lines( Paths.get(superUserFilepath), StandardCharsets.UTF_8)) 
		{
			stream.forEach(s -> getSuperUserInfo.append(s).append("\n"));
			String fileInfo = getSuperUserInfo.toString();
			String[] superUserInfo = fileInfo.split(" ");
			superUserInfo[1] = superUserInfo[1].substring(0,(superUserInfo[1].length() - 1));
			String[] loginInfo = login();
			if (superUserInfo[0].equals(loginInfo[0]) && superUserInfo[1].equals(loginInfo[1])){
				return true;
			}
		} catch (IOException e) {
			System.out.println("Super user info file not found.");
			e.printStackTrace();
		}
		return false;
	}
	
	public int getAllUsers(RegUser superUser) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from BANK_CUST";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = results.getMetaData();
			int numColumns = rsmd.getColumnCount();
			System.out.println("Customer Accounts\n=================\nCustomer ID\tFirst Name\tLast Name\tUser Name");
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 5 != 0) {
						System.out.print(columnValue);
					}
					if (i % 5 == 0) {
						System.out.println();
					}
				}
			}
			sql = "select * from BANK_CHECK";
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql);
			rsmd = results.getMetaData();
			numColumns = rsmd.getColumnCount();
			System.out.println("Checking Accounts\n=================\nCustomer ID\tAccount ID\tAmount");
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 3 == 0) {
						String formatMoney = formatter.format(Double.parseDouble(columnValue));
						System.out.println(formatMoney);
					} else {
						System.out.print(columnValue);
					}
				}
			}
			System.out.println("\nSavings Accounts\n================\nCustomer ID\tAccount ID\tAmount");
			sql = "select * from BANK_SAVE";
			results = stmt.executeQuery(sql);
			rsmd = results.getMetaData();
			numColumns = rsmd.getColumnCount();
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 3 == 0) {
						String formatMoney = formatter.format(Double.parseDouble(columnValue));
						System.out.println(formatMoney);
					} else {
						System.out.print(columnValue);
					}
				}
			}
			System.out.println("\nWould you like to:\n1)Update an account\n0)Return to main menu");
			int menu = Driver.getInt(1,1);
			if (menu == 1) {
				updateUser(superUser);
			}
		} catch (SQLException e) {
			System.out.println("No accounts found.");
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return -1;
	}

	private void updateUser(RegUser superUser) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			System.out.print("Update 1)Checking or 2)Savings? ");
			int accountType = Integer.parseInt(scanner.nextLine());
			System.out.print("Update which account? ");
			int accountNum = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter amount to adjust: ");
			double adjust = Double.parseDouble(scanner.nextLine());
			double balance = 0;
			if (accountType == 1){
				String sql = "select * from BANK_CHECK where CHECK_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				sql = "call UPDATE_BANK_CHECK(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance + adjust);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance + adjust);
				System.out.println("New balance: " + formatMoney);
			}
			if (accountType == 2){
				String sql = "select * from BANK_SAVE where SAVE_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				sql = "call UPDATE_BANK_SAVE(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance + adjust);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance + adjust);
				System.out.println("New balance: " + formatMoney);
			}
		} catch (SQLException e) {
			System.out.println("No accounts found.");
		} catch (BadAccountNumberException bane) {
			System.out.println("No matching account number.");
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return;
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser() {

		return null;
	}

	public int getAccounts(RegUser user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from BANK_CHECK where CUST_ID = '" + user.getId() + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = results.getMetaData();
			int numColumns = rsmd.getColumnCount();
			System.out.println("Checking Accounts\n=================\nCustomer ID\tAccount ID\tAmount");
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 3 == 0) {
						String formatMoney = formatter.format(Double.parseDouble(columnValue));
						System.out.println(formatMoney);
					} else {
						System.out.print(columnValue);
					}
				}
			}
			System.out.println("\nSavings Accounts\n================\nCustomer ID\tAccount ID\tAmount");
			sql = "select * from BANK_SAVE where CUST_ID = '" + user.getId() + "'";
			results = stmt.executeQuery(sql);
			rsmd = results.getMetaData();
			numColumns = rsmd.getColumnCount();
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 3 == 0) {
						String formatMoney = formatter.format(Double.parseDouble(columnValue));
						System.out.println(formatMoney);
					} else {
						System.out.print(columnValue);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("No accounts found.");
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		int menu = -1;
		System.out.println("\nWould you like to:\n1)Withdraw\n2)Deposit\n0)Return to main menu");
		menu = Driver.getInt(1,2);
		if (menu == 1) {
			withdraw(user);
		}
		if (menu == 2) {
			deposit(user);
		}
		return -1;
	}

	private void withdraw(RegUser user) {
		int accountType;
		double withdrawal;
		double balance = 0;
		int accountNum;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			System.out.print("Withdraw from 1) Checking or 2) Savings: ");
			accountType = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter account number: ");
			accountNum = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter amount to withdraw: ");
			withdrawal = Double.parseDouble(scanner.nextLine());
			if (accountType == 1){
				String sql = "select * from BANK_CHECK where CHECK_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				if (withdrawal > balance) {
					throw new OverdraftException("Error: insufficient funds.");
				}
				sql = "call UPDATE_BANK_CHECK(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance - withdrawal);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance - withdrawal);
				System.out.println("New balance: " + formatMoney);
			}
			if (accountType == 2){
				String sql = "select * from BANK_SAVE where SAVE_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				if (withdrawal > balance) {
					throw new OverdraftException("Error: insufficient funds.");
				}
				sql = "call UPDATE_BANK_SAVE(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance - withdrawal);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance - withdrawal);
				System.out.println("New balance: " + formatMoney);
			}
		} catch (OverdraftException oe){
			System.out.println("Insufficient funds.");
		} catch (BadAccountNumberException bane) {
			System.out.println("No matching account number.");
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return;
	}
	
	private void deposit(RegUser user) {
		int accountType;
		double deposit;
		double balance = 0;
		int accountNum;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			System.out.print("Deposit into 1) Checking or 2) Savings: ");
			accountType = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter account number: ");
			accountNum = Integer.parseInt(scanner.nextLine());
			System.out.print("Enter amount to deposit: ");
			deposit = Double.parseDouble(scanner.nextLine());
			if (accountType == 1){
				String sql = "select * from BANK_CHECK where CHECK_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				sql = "call UPDATE_BANK_CHECK(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance + deposit);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance + deposit);
				System.out.println("New balance: " + formatMoney);
			}
			if (accountType == 2){
				String sql = "select * from BANK_SAVE where SAVE_ID = '" + accountNum + "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()){
					if (results.getInt(2) != accountNum) {
						throw new BadAccountNumberException("Error: no matching account number.");
					}
					balance = results.getDouble(3);
				}
				sql = "call UPDATE_BANK_SAVE(?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, accountNum);
				cs.setDouble(2, balance + deposit);
				cs.executeUpdate();
				String formatMoney = formatter.format(balance + deposit);
				System.out.println("New balance: " + formatMoney);
			}
		} catch (BadAccountNumberException bane) {
			System.out.println("No matching account number.");
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
	return;
	}

	public int createNewAccount(RegUser user) {
		int accountType;
		double deposit;
		System.out.print("Create a new 1) Checking or 2) Savings account: ");
		accountType = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter amount to deposit: ");
		deposit = Double.parseDouble(scanner.nextLine());
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			if (accountType == 1) {
				String sql = "call INSERT_BANK_CHECK(?,?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, user.getId());
				cs.setInt(2, 1);
				cs.setDouble(3, deposit);
				cs.executeUpdate();
				String formatMoney = formatter.format(deposit);
				System.out.println("Account successfully created.  New balance: " + formatMoney);
			} else if (accountType == 2) {
				String sql = "call INSERT_BANK_SAVE(?,?,?)";
				CallableStatement cs = conn.prepareCall(sql);
				cs.setInt(1, user.getId());
				cs.setInt(2, 1);
				cs.setDouble(3, deposit);
				cs.executeUpdate();
				String formatMoney = formatter.format(deposit);
				System.out.println("Account successfully created.  New balance: " + formatMoney);
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return -1;
	}

	public int deleteAccount(RegUser user) {
		boolean emptyAccounts = false;
		int accountNum = 0;
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from BANK_CHECK where CUST_ID = '" + user.getId() + 
					"' and CHECK_AMT = 0";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = results.getMetaData();
			int numColumns = rsmd.getColumnCount();
			if (results.next() == false) {
				System.out.println("No empty checking accounts.");
			}
			else {
				emptyAccounts = true;
				System.out.println("Checking Accounts\n=================\nCustomer ID\tAccount ID\tAmount");
				do {
					for (int i = 1; i <= numColumns; i++) {
						if (i > 1) {
							System.out.print("\t");
						}
						String columnValue = results.getString(i);
						if (i > 1) {
							System.out.print("\t");
						}
						if (i % 3 == 0) {
							String formatMoney = formatter.format(Double.parseDouble(columnValue));
							System.out.println(formatMoney);
						} else {
							System.out.print(columnValue);
						}
					}
				} while (results.next()); 
			}
			
			sql = "select * from BANK_SAVE where CUST_ID = '" + user.getId() + 
					"' and SAVE_AMT = 0";
			results = stmt.executeQuery(sql);
			rsmd = results.getMetaData();
			numColumns = rsmd.getColumnCount();
			if (results.next() == false) {
				System.out.println("No empty savings accounts.");
			}
			else {
				emptyAccounts = true;
				System.out.println("\nSavings Accounts\n================\nCustomer ID\tAccount ID\tAmount");
				do {
					for (int i = 1; i <= numColumns; i++) {
						if (i > 1) {
							System.out.print("\t");
						}
						String columnValue = results.getString(i);
						if (i > 1) {
							System.out.print("\t");
						}
						if (i % 3 == 0) {
							String formatMoney = formatter.format(Double.parseDouble(columnValue));
							System.out.println(formatMoney);
						} else {
							System.out.print(columnValue);
						}
					}
				} while (results.next()); 
			}
		if (emptyAccounts == false) {
			System.out.println("Unable to delete an account if account is not empty.");
		}
		else {
			System.out.print("Which account would you like to delete? ");
			accountNum = Integer.parseInt(scanner.nextLine());
			int deleteSuccessful = 0;
			sql = "call DELETE_BANK_CHECK(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, accountNum);
			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
			cs.executeUpdate();
			deleteSuccessful = deleteSuccessful + cs.getInt(2);
			sql = "call DELETE_BANK_SAVE(?,?)";
			cs = conn.prepareCall(sql);
			cs.setInt(1, accountNum);
			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
			cs.executeUpdate();
			deleteSuccessful = deleteSuccessful + cs.getInt(2);
			if (deleteSuccessful > 0) {
				System.out.println("Account succesfully deleted.");
			}
			else {
				System.out.println("Account not deleted.  No matching empty account with that account number.");
			}
		}

		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return -1;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public int superUserdeleteAccount(RegUser superUser) {
		int accountNum = 0;
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select * from BANK_CUST";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = results.getMetaData();
			int numColumns = rsmd.getColumnCount();
			System.out.println("Customer Accounts\n=================\nCustomer ID\tFirst Name\tLast Name\tUser Name");
			while (results.next()) {
				for (int i = 1; i <= numColumns; i++) {
					if (i > 1) {
						System.out.print("\t");
					}
					String columnValue = results.getString(i);
					if (i > 1) {
						System.out.print("\t");
					}
					if (i % 5 != 0) {
						System.out.print(columnValue);
					}
					if (i % 5 == 0) {
						System.out.println();
					}
				}
			}
			System.out.print("Delete which user (and associated accounts)? ");
			accountNum = Integer.parseInt(scanner.nextLine());
			int deleteSuccessful = 0;
			sql = "call DELETE_BANK_CUST(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, accountNum);
			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
			cs.executeUpdate();
			deleteSuccessful = deleteSuccessful + cs.getInt(2);
			if (deleteSuccessful > 0) {
				System.out.println("Account succesfully deleted.");
			}
			else {
				System.out.println("Account not deleted.  No matching account number.");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return -1;
	}

	public int superUserdeleteAll(RegUser superUser) {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			System.out.print("Delete all users?  (1)Yes 2)No) ");
			int confirmDelete = 0;
			confirmDelete = Integer.parseInt(scanner.nextLine());
			if (confirmDelete == 1) {
				String sql = "DROP TABLE BANK_CHECK";
				Statement stmt = conn.createStatement();
				stmt.execute(sql);
				sql = "DROP TABLE BANK_SAVE";
				stmt = conn.createStatement();
				stmt.execute(sql);
				sql = "DROP TABLE BANK_CUST";
				stmt = conn.createStatement();
				stmt.execute(sql);
				System.out.println("Delete successful.");
			}
			else {
				System.out.println("Returning to main menu...");
			}
		}
		catch (NumberFormatException nfe) {
			System.out.println("Invalid entry.  Please try again.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch  (NullPointerException npe) {
			System.out.println("No accounts found for this user.");
		}
		return -1;
	}
}
