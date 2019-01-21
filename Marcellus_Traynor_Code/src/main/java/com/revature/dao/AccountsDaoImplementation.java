package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class AccountsDaoImplementation implements AccountsDAO 
{
	private static AccountsDaoImplementation accountsDao;
	final static Logger log = Logger.getLogger(AccountsDaoImplementation.class);
	private AccountsDaoImplementation() {}
	
	
	public static AccountsDaoImplementation getAccountsDao()
	{
		if(accountsDao == null)
		{
			accountsDao = new AccountsDaoImplementation();
		}
		return accountsDao;
	}
	
	//Accounts/Balances
	public static void accountsMenu(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("Welcome to your accounts viewer! What would you like to do?\n");
			System.out.println("UserID\tAccountID\tAccount Type\t\tBalance");
			System.out.println("******************************************************************");
			
			String sql = "select * from Bank_Accounts where U_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getInt(2) + "\t\t");
				System.out.print(rs.getString(3) + "         ");
				System.out.print(rs.getDouble(4) + "\n\n");
			}
			
			System.out.println("[1] Create a new account\t\t[3] Withdraw/Deposit");
			System.out.println("[2] Delete an empty account\t\t[4] Logout and Exit Portal");
				
			int choice;
			
			do
			{
				choice = sc.nextInt();
				
				switch(choice)
				{
				case 1: newAccount(user);
						break;
				case 2: deleteAccount(user);
						break;
				case 3: withdrawDeposit();
						break;
				case 4: System.out.println("Thank you for banking with JDBCBank. Goodbye :)\n");
						sc.close();
						System.exit(0);
				default: System.out.println("Invalid input, try again.");		
				}
				System.out.println("Welcome to your accounts viewer! What would you like to do?\n");
				System.out.println("UserID\tAccountID\tAccount Type\t\tBalance");
				System.out.println("******************************************************************");
				
				String sql1 = "select * from Bank_Accounts where U_ID = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, user.getUserId());
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next())
				{
					System.out.print(rs1.getInt(1) + "\t");
					System.out.print(rs1.getInt(2) + "\t\t");
					System.out.print(rs1.getString(3) + "         ");
					System.out.print(rs1.getDouble(4) + "\n\n");
				}
				
				System.out.println("[1] Create a new account\t\t[3] Withdraw/Deposit");
				System.out.println("[2] Delete an empty account\t\t[4] Logout and Exit Portal");
			}
			while(choice != 4);
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in accountsMenu Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
			
	//Create New Account
	public static void newAccount(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("Which type of account would you like to create?\n"
					+ "[1] Checking Account\t\t[2] Savings Account\n[3] Return to Accounts Menu");
			int choice;
			
			do
			{
				double money;
				choice = sc.nextInt();
				switch(choice)
				{
				case 1: System.out.println("Enter a monetary amount to deposit into your new account:");
						money = sc.nextDouble();
						
						String sql = "call create_account(?,?,?,?)";
						CallableStatement cs = conn.prepareCall(sql);
						cs.setInt(1, user.getUserId());
						cs.setString(2, "Checking Account");
						cs.setDouble(3, money);
						cs.registerOutParameter(4, Types.INTEGER);
						cs.executeUpdate();
						if(cs.getInt(4) > 0)
						{
							System.out.println("Your Checking Account has been created.\n");
						}
						else
						{
							System.out.println("Could not create your account");
						}
						break;
						
				case 2: System.out.println("Enter a monetary amount to deposit into your new account:");
						money = sc.nextDouble();
				
						String sql1 = "call create_account(?,?,?,?)";
						CallableStatement cs1 = conn.prepareCall(sql1);
						cs1.setInt(1, user.getUserId());
						cs1.setString(2, "Savings Account ");
						cs1.setDouble(3, money);
						cs1.registerOutParameter(4, Types.INTEGER);
						cs1.executeUpdate();
						if(cs1.getInt(4) > 0)
						{
							System.out.println("Your Savings Account has been created.\n");
						}
						else
						{
							System.out.println("Could not create your account");
						}
						break;
						
				case 3: break;
				default: System.out.println("Invalid input, try again.");		
				}
				if(choice == 1 | choice == 2)
				{
				System.out.println("Would you like to create another account?\n"
						+ "[1] Checking Account\t\t[2] Savings Account\n[3] Return to Accounts Menu");
				}
			}
			while(choice != 3);
			conn.close();
			
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in newAccount Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
			
	//Delete Empty Account
	public static void deleteAccount(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("Enter the account number of the account you would like to delete:");
			int choice;
			choice = sc.nextInt();
			
			String sql = "select balance from Bank_Accounts where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, choice);
			ResultSet rs = ps.executeQuery();
			rs.next();
			double balance = rs.getDouble(1); 
			
			if(balance == 0.0)
			{
				String sql1 = "delete from Bank_Accounts where account_id = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, choice);
				ps1.executeUpdate();
				System.out.println("Account successfully deleted!\n");
			}
			else
			{
				System.out.println("This account must be empty before being deleted. Withdraw funds and try again.\n");
			}
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in deleteAccount Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
			
	//Withdraw/Deposit
	public static void withdrawDeposit()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("Enter the account number of the account you would like to access:");
			int accountNum;
			int choice;
			accountNum = sc.nextInt();	
			
			do
			{
				System.out.println("\nWhat kind of transaction would you like to make?\n"
						+ "[1] Withdraw\t\t[2] Deposit\n[3] Return to Accounts Menu");
				
				choice = sc.nextInt();
			
				switch(choice)
				{
				case 1: System.out.println("How much would you like to withdraw?");
						double withdraw = sc.nextDouble();
						
						String sql = "select balance from Bank_Accounts where account_id = " + accountNum;
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery(sql);
						rs.next();
						double balance = rs.getDouble(1);
						
						if(balance >= withdraw)
						{
							double newBalance = balance - withdraw;
							String sql2 = "update Bank_Accounts set balance = " + newBalance +
											" where account_id = " + accountNum;
							PreparedStatement ps2 = conn.prepareStatement(sql2);
							ps2.execute();
							System.out.println("Withdrawal successful!");
						}
						else
						{
							System.out.println("Overdraft: you can't withdraw more than what is in your account.\n");
						}
						break;
						
				case 2: System.out.println("How much would you like to deposit?");
						double deposit = sc.nextDouble();
						
						String sql3 = "select balance from Bank_Accounts where account_id = " + accountNum;
						PreparedStatement ps1 = conn.prepareStatement(sql3);
						ResultSet rs1 = ps1.executeQuery(sql3);
						rs1.next();
						double balance2 = rs1.getDouble(1);
						
						double newBalance2 = balance2 + deposit;
						String sql4 = "update Bank_Accounts set balance = " + newBalance2 +
								" where account_id = " + accountNum;
						PreparedStatement ps4 = conn.prepareStatement(sql4);
						ps4.execute();
						
						System.out.println("Deposit successful!");
						break;
					
				case 3: break;
				default: System.out.println("Invalid input, try again.");
				}
			}
			while(choice != 3);
			conn.close();
			
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in withdrawDeposit Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
}