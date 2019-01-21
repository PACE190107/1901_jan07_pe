package com.revature.functions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.revature.account.BankAccount;
import com.revature.user.AuthenticatedUser;
import com.revature.utils.JDBCConnectionUtil;

public class JDBCFunctionsImplementation implements JDBCFunctions {
	
	Scanner sc = new Scanner(System.in);
	BankAccount tester = new BankAccount();
	
	@Override
	public void firstChoice() throws SQLException {
		System.out.println("1)Log into existing account.          2)Create new account.");
		int userChoice = sc.nextInt();
		if (userChoice ==1) {
			return;
		}else if (userChoice ==2) {
			createUserAccount();
			firstChoice();
		}else {
			System.out.println("Not a valid selecton!");
			firstChoice();
		}
	}
	 
	//NEED TO MAKE THINGS COMMIT
	@Override
	public void createUserAccount() throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
		System.out.println("Please enter a username");
		String username = sc.next();
		System.out.println("Please enter a password");
		String password = sc.next();
			String sql = "{call newUser (?,?)}";	
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			if (ps.executeUpdate() > 0) {
				System.out.println("Account Created!");
			}
				else {
				System.out.println("An error occured! Please try again.");
			}	
			sql = ("CREATE USER " + username + " IDENTIFIED BY " + password );
			String sql2 = (" grant connect, resource to " + username);
			String sql3 = (" grant DBA to " + username + " with admin option");
			ps = conn.prepareCall(sql);
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql2);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql3);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
				ps.close();
				conn.close();
			} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
	@Override
	public void viewMenu(AuthenticatedUser user) throws SQLException {
		int userChoice = 0;
		
		System.out.println("Welcome to the Bank!");
		System.out.println("Please enter a number to select an option...");
		System.out.println("1: View Existing Accounts     4: Make a Deposit");
		System.out.println("2: Create a New Bank Account  5: Make a Withdrawl");
		System.out.println("3: Delete an Account          6: View Transaction History");
		System.out.println("                              7: Logout");
		
		userChoice = sc.nextInt();

		switch(userChoice) {
		case 1:
			System.out.println("View Existing Accounts ");
			viewAccounts(user);
			System.out.println("\n\n");
			pressAnyKeyToContinue();
			viewMenu(user);
			break;
		case 2:
			System.out.println("Create a New Account");
			createAccount(user);
			System.out.println("\n\n");
			viewMenu(user);
			break;
		case 3:
			System.out.println("Delete an Account");
			deleteAccount(user);
			System.out.println("\n\n");
			viewMenu(user);
			break;
		case 4:
			System.out.println("Make a Deposit");
			makeDeposit(user);
			System.out.println("\n\n");
			viewMenu(user);
			break;
		case 5:
			System.out.println("Make a Withdrawl");
			makeWithdrawl(user);
			System.out.println("\n\n");
			viewMenu(user);
			break;
		case 6:
			System.out.println("View Transaction History");
			System.out.println("\n\n");
			viewMenu(user);
			break;
		case 7:
			System.out.println("Logout");
			break;
		default:
			System.out.println(userChoice + " is not a valid choice. Try again. \n");
			viewMenu(user);
		}
		
	}
	
	@Override
	public boolean logIn(String username, String password) throws SQLException {
		try {Connection conn = JDBCConnectionUtil.getConnection(username, password);
			conn.close();
		} catch (SQLException e) {
			System.out.println("\nInvalid credentials!\n");
			return false;
		}
		return true;
	}
	
	@Override
	public void viewAccounts(AuthenticatedUser user) throws  SQLException{
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			String getUserId = ("select USER_ID from ADMIN.BANK_USER WHERE USERNAME = '"  + user.getUsername() + "'");
			Statement stmt = conn.createStatement();
			ResultSet getSqlUser = stmt.executeQuery(getUserId);
			int userId = 0;
			while(getSqlUser.next()) {
				userId = getSqlUser.getInt("USER_ID");
			}
			String sql = "select * from ADMIN.BANK_ACCOUNT WHERE USER_ID = " + userId ;
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				System.out.print(results.getString("ACCOUNT_TYPE"));
				System.out.print(" Account ID: " + results.getInt("ACCOUNT_ID"));
				System.out.println(" Balance: " + results.getDouble("BALANCE"));
				
			}
			stmt.close();
			getSqlUser.close();
			results.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Need to remove the hard code.
	//Encountering an issue with case-sensitivity on username/password
	@Override
	public void createAccount(AuthenticatedUser user) throws  SQLException{
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			int accountTypeCheck = 0;
			String accountType = " ";
			String getUserId = ("select USER_ID from ADMIN.BANK_USER WHERE USERNAME = '"  + user.getUsername() + "'");
			Statement stmt = conn.createStatement();
			//NEED TO USE AN EXECUTE UPDATE STATEMENT????
			ResultSet getSqlUser = stmt.executeQuery(getUserId);
			int userId = 0;
			while(getSqlUser.next()) {
				userId = getSqlUser.getInt("USER_ID");
			}
			System.out.println("1) Checking         2)Savings");
			System.out.print("Enter the number for the type of account: ");
			accountTypeCheck = sc.nextInt();
			if (accountTypeCheck ==1) 
				accountType = "Checking";
			else if (accountTypeCheck == 2)
				accountType = "Savings";
			else {
				System.out.println( "\n" +  accountTypeCheck + " is not a valid selection!");
				createAccount(user);
				return;
			}	
			String sql = "{call ADMIN.newAccount(?,?)}";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, accountType);
			ps.setInt(2, userId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Account Created!");
			}
				else {
				System.out.println("An error occured! Please try again.");
				createAccount(user);
			}
			stmt.close();
			getSqlUser.close();
			ps.close();
			conn.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//MAke able to delete while balance is not 0?
	@Override
	public void deleteAccount(AuthenticatedUser user) throws  SQLException{
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			int accountTypeCheck = 0;
			viewAccounts(user);
			System.out.print("\nEnter the account number of the account you wish to delete: ");
			accountTypeCheck = sc.nextInt();
			String sql = "delete from ADMIN.BANK_ACCOUNT where ACCOUNT_ID = " + accountTypeCheck;
			CallableStatement ps = conn.prepareCall(sql);
			if (ps.executeUpdate() > 0) {
				System.out.println("Account deleted!");
			}else {
				System.out.println("An error occured! Please try again.");
				deleteAccount(user);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//Still need the user id so users can't access other users accounts
	//It won't change balance of another account, but still shows transaction successful message 
	//ERROR: ANY account number can be typed in. Need a way to tell if account ID is valid...
	@Override
	public void makeDeposit(AuthenticatedUser user) throws  SQLException{
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			viewAccounts(user);
			String getUserId = ("select USER_ID from ADMIN.BANK_USER WHERE USERNAME = '"  + user.getUsername() + "'");
			Statement stmt = conn.createStatement();
			//NEED TO USE AN EXECUTE UPDATE STATEMENT????
			ResultSet getSqlUser = stmt.executeQuery(getUserId);
			int userId = 0;
			while(getSqlUser.next()) {
				userId = getSqlUser.getInt("USER_ID");
			}
			System.out.print("\nEnter the account number of the account you wish to deposit money into: ");
			int accountId = sc.nextInt();
			System.out.print("\nEnter an ammount to deposit: ");
			float balance = sc.nextFloat();
			String sql = "{call ADMIN.changeBalance(?,?,?)}";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setFloat(1, balance);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Deposit Succesful!");
			}
				else {
				System.out.println("An error occured! Please try again.");
				makeDeposit(user);
			}
			stmt.close();
			getSqlUser.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void makeWithdrawl(AuthenticatedUser user) throws  SQLException{
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			viewAccounts(user);
			String getUserId = ("select USER_ID from ADMIN.BANK_USER WHERE USERNAME = '"  + user.getUsername() + "'");
			Statement stmt = conn.createStatement();
			//NEED TO USE AN EXECUTE UPDATE STATEMENT????
			ResultSet getSqlUser = stmt.executeQuery(getUserId);
			int userId = 0;
			while(getSqlUser.next()) {
				userId = getSqlUser.getInt("USER_ID");
			}
			System.out.print("\nEnter the account number of the account you wish to withdraw funds from: ");
			int accountId = sc.nextInt();
			System.out.print("\nEnter an ammount to withdraw: ");
			float balance = sc.nextFloat();
			balance = balance * (-1);
			String sql = "{call ADMIN.changeBalance(?,?,?)}";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setFloat(1, balance);
			ps.setInt(2, accountId);
			ps.setInt(3, userId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Withdrawl Succesful!");
			}
				else {
				System.out.println("An error occured! Please try again.");
				makeWithdrawl(user);
			}
			stmt.close();
			getSqlUser.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//https://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code
	@Override
	 public void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
	
	@Override
	public boolean testSuper(AuthenticatedUser user) {
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			Statement stmt = conn.createStatement();
			String sql = ("select super from ADMIN.BANK_USER WHERE USERNAME = '"  + user.getUsername() + "'");
			ResultSet getSqlUser = stmt.executeQuery(sql);
			int checkSuper = 0;
			while(getSqlUser.next()) {
				checkSuper = getSqlUser.getInt("super");
			}
			if (checkSuper == 1) {
				return true;
			}
			stmt.close();
			getSqlUser.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	@Override
	public void superMenu(AuthenticatedUser user) throws SQLException {
		int userChoice = 0;
		System.out.println("Please enter a number to select an option...");
		System.out.println("1: View Users                 4: Delete a User");
		System.out.println("2: Create a User              5: Logout");
		System.out.println("3: Update a User");
		userChoice = sc.nextInt();
		
		switch(userChoice) {
		case 1:
			System.out.println("View Users ");
			superViewUser(user);
			System.out.println("\n\n");
			pressAnyKeyToContinue();
			superMenu(user);
			break;
		case 2:
			System.out.println("Create a User");
			createUserAccount();
			System.out.println("\n\n");
			superMenu(user);
			break;
		case 3:
			System.out.println("Update a User");
			superUpdate(user);
			System.out.println("\n\n");
			superMenu(user);
			break;
		case 4:
			System.out.println("Delete a User");
			superDeleteUser(user);
			System.out.println("\n\n");
			superMenu(user);
			break;
		case 5:
			System.out.println("Logout");
			break;
		default:
			System.out.println(userChoice + " is not a valid choice. Try again. \n");
			superMenu(user);
		}
		
	}
	
	@Override
	public void superViewUser(AuthenticatedUser user) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword());){
			Statement stmt = conn.createStatement();
			String sql = "select USERNAME, PASSPHRASE, BANK_USER.USER_ID, SUPER, \r\n" + 
					"ACCOUNT_TYPE, ACCOUNT_ID, BALANCE\r\n" + 
					"FROM ADMIN.BANK_USER FULL JOIN ADMIN.BANK_ACCOUNT\r\n" + 
					"on BANK_USER.USER_ID = BANK_ACCOUNT.USER_ID\r\n" + 
					"order by UPPER (BANK_USER.USERNAME)";
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				System.out.print("Username: " + results.getString("USERNAME") + "   ");
				System.out.print("Password: " +results.getString("PASSPHRASE")+ "   ");
				System.out.print("User ID: " +results.getInt("USER_ID")+ "   ");
				System.out.print("Is Super?: " +results.getInt("SUPER")+ "   ");
				System.out.print("Account Type: " +results.getString("ACCOUNT_TYPE")+ "   ");
				System.out.print("Account ID: " +results.getInt("ACCOUNT_ID")+ "   ");
				System.out.println("Balance: " +results.getDouble("BALANCE")+ "   ");	
				System.out.println("----------------------------------------------------------------------");
			}
			stmt.close();
			results.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void superUpdate(AuthenticatedUser user) throws SQLException {
		System.out.print("Enter the User ID for the account to update: ");
		int userId = sc.nextInt();
		System.out.println("What would you like to update?");
		System.out.println("1: Change Username");
		System.out.println("2: Change Password");
	//	System.out.println("3: Give Admin Privileges");
		int userChoice = sc.nextInt();
		switch(userChoice) {
		case 1:
			superChangeUser(user, userId);
			System.out.println("\n\n");
			break;
		case 2:
			superChangePassword(user, userId);
			System.out.println("\n\n");
			break;
		default:
			System.out.println("Not a valid selection!");
			superMenu(user);
		}	
	}
	
	@Override
	public void superChangeUser(AuthenticatedUser user, int userId) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			System.out.println("What would you like the new username to be: ");
			String newUsername = sc.next();
			String getUsername = ("select USERNAME from ADMIN.BANK_USER WHERE USER_ID = "  + userId);
			Statement stmt = conn.createStatement();
			ResultSet getSqlUser = stmt.executeQuery(getUsername);
			String userName = "";
			while(getSqlUser.next()) {
				userName = getSqlUser.getString("USERNAME");
			}
			String getPassword = ("select PASSPHRASE from ADMIN.BANK_USER WHERE USER_ID = '"  + userId + "'");
			Statement stmt2 = conn.createStatement();
			ResultSet getSqlPass = stmt.executeQuery(getPassword);
			String password = "";
			while(getSqlPass.next()) {
				password = getSqlPass.getString("PASSPHRASE");
			}
			String drop = ("DROP USER " + userName);
			ResultSet dropUser = stmt.executeQuery(drop);
			String addToTable = ("UPDATE ADMIN.BANK_USER SET USERNAME = '" + newUsername + "' WHERE USERNAME = '" + userName + "'" );
			ResultSet addedToTable = stmt.executeQuery(addToTable);
			String sql = ("CREATE USER " + newUsername + " IDENTIFIED BY " + password );
			String sql2 = (" grant connect, resource to " + newUsername);
			String sql3 = (" grant DBA to " + newUsername + " with admin option");
			CallableStatement ps = conn.prepareCall(sql);
			ps = conn.prepareCall(sql);
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql2);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql3);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			System.out.println("Username Changed!");
			getSqlPass.close();
			getSqlUser.close();
			dropUser.close();
			addedToTable.close();
			stmt2.close();
			stmt.close();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public void superChangePassword(AuthenticatedUser user, int userId) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			System.out.println("What would you like the new password to be: ");
			String newPassword = sc.next();
			
			String getUsername = ("select USERNAME from ADMIN.BANK_USER WHERE USER_ID = "  + userId);
			Statement stmt = conn.createStatement();
			ResultSet getSqlUser = stmt.executeQuery(getUsername);
			String userName = "";
			while(getSqlUser.next()) {
				userName = getSqlUser.getString("USERNAME");
			}
			String getPassword = ("select PASSPHRASE from ADMIN.BANK_USER WHERE USER_ID = '"  + userId + "'");
			Statement stmt2 = conn.createStatement();
			ResultSet getSqlPass = stmt.executeQuery(getPassword);
			String password = "";
			while(getSqlPass.next()) {
				password = getSqlPass.getString("PASSPHRASE");
			}
			String drop = ("DROP USER " + userName);
			ResultSet dropUser = stmt.executeQuery(drop);
			
			System.out.println("after the drop user");
			String addToTable = ("UPDATE ADMIN.BANK_USER SET PASSPHRASE = '" + newPassword + "' WHERE PASSPHRASE = '" + password + "'" );
			ResultSet addedToTable = stmt.executeQuery(addToTable);
			String sql = ("CREATE USER " + userName + " IDENTIFIED BY " + newPassword );
			String sql2 = (" grant connect, resource to " + userName);
			String sql3 = (" grant DBA to " + userName + " with admin option");
			CallableStatement ps = conn.prepareCall(sql);
			ps = conn.prepareCall(sql);
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql2);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			ps = conn.prepareCall(sql3);	
				if (ps.executeUpdate() > 0) {
					System.out.println("Account Created!");
				}
			System.out.println("Password Changed!");
			getSqlPass.close();
			getSqlUser.close();
			dropUser.close();
			addedToTable.close();
			stmt2.close();
			stmt.close();
			ps.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	
	@Override
	public void superDeleteUser(AuthenticatedUser user) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection(user.getUsername(), user.getPassword())){
			System.out.print("Please Enter the User ID of the User that you want to delete:");
			int userId = sc.nextInt();
			String getUsername = ("select USERNAME from ADMIN.BANK_USER WHERE USER_ID = "  + userId);
			Statement stmt = conn.createStatement();
			ResultSet getSqlUser = stmt.executeQuery(getUsername);
			String userName = "";
			while(getSqlUser.next()) {
				userName = getSqlUser.getString("USERNAME");
			}
			String deleteFromAccount = "DELETE FROM ADMIN.BANK_ACCOUNT WHERE USER_ID = " + userId;
			ResultSet deleteAccount = stmt.executeQuery(deleteFromAccount);
			String deleteFromTable = "DELETE FROM ADMIN.BANK_USER WHERE USER_ID = " + userId;
			ResultSet deleteUser = stmt.executeQuery(deleteFromTable);
			String dropUser = "DROP USER " + userName;
			ResultSet drop = stmt.executeQuery(dropUser);
			stmt.close();
			deleteAccount.close();
			getSqlUser.close();
			deleteUser.close();
			drop.close();
			conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		System.out.println("User Deleted");
	}
}
