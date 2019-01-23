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
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exceptions.GhostAccount;
import com.revature.exceptions.InvalidOption;
import com.revature.exceptions.OverdraftProtection;
import com.revature.exceptions.ThrowingAwayMoney;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JDBCConnectionUtil;

public class AccountsDaoImplementation implements AccountsDAO 
{
	private static AccountsDaoImplementation accountsDao;
	final static Logger log = Logger.getLogger(AccountsDaoImplementation.class);
	public AccountsDaoImplementation() {}
	
	
	public static AccountsDaoImplementation getAccountsDao()
	{
		if(accountsDao == null)
		{
			accountsDao = new AccountsDaoImplementation();
		}
		return accountsDao;
	}
	
	public static void accountsMenu(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("\nWELCOME TO YOUR ACCOUNTS VIEWER! WHAT WOULD YOU LIKE TO DO?\n");
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
			System.out.println("[2] Delete an empty account\t\t[4] Logout");
				
			int choice;
			
			do
			{
				choice = sc.nextInt();
				
				try
				{
					switch(choice)
					{
					case 1: newAccount(user);
							break;
					case 2: deleteAccount(user);
							break;
					case 3: withdrawDeposit();
							break;
					case 4: System.out.println("\nThank you for banking with JDBCBank. Goodbye :)\n");
							UserService.welcome();
					default: throw new InvalidOption();		
					}
				}
				catch(InvalidOption e)
				{
					System.out.println(e.getMessage());
				}
				System.out.println("\nWELCOME TO YOUR ACCOUNTS VIEWER! WHAT WOULD YOU LIKE TO DO?\n");
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
				System.out.println("[2] Delete an empty account\t\t[4] Logout");
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
			
	public static void newAccount(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("\nWHICH TYPE OF ACCOUNT WOULD YOU LIKE TO CREATE?\n\n"
					+ "[1] Checking Account\t\t[2] Savings Account\n[3] Return to Accounts Menu");
			int choice;
			
			do
			{
				double money;
				choice = sc.nextInt();
				
				try
				{
					switch(choice)
					{
					case 1: System.out.println("\nENTER A MONETARY AMOUNT TO DEPOSIT INTO YOUR ACCOUNT:");
							money = sc.nextDouble();
							
							if(money < 0.0)
							{
								System.out.println("\nYOU CANNOT DEPOSIT A NEGATIVE AMOUNT. TRY AGAIN\n");
								break;
							}
							
							String sql = "call create_account(?,?,?,?)";
							CallableStatement cs = conn.prepareCall(sql);
							cs.setInt(1, user.getUserId());
							cs.setString(2, "Checking Account");
							cs.setDouble(3, money);
							cs.registerOutParameter(4, Types.INTEGER);
							cs.executeUpdate();
							if(cs.getInt(4) > 0)
							{
								System.out.println("\nYOUR CHECKING ACCOUNT HAS BEEN SUCCESSFULLY CREATED.\n");
							}
							else
							{
								System.out.println("\nCOULD NOT CREATE YOUR ACCOUNT.\n");
							}
							break;
							
					case 2: System.out.println("\nENTER A MONETARY AMOUNT TO DEPOSIT INTO YOUR ACCOUNT:");
							money = sc.nextDouble();
					
							if(money < 0.0)
							{
								System.out.println("\nYOU CANNOT DEPOSIT A NEGATIVE AMOUNT. TRY AGAIN\n");
								break;
							}
							
							String sql1 = "call create_account(?,?,?,?)";
							CallableStatement cs1 = conn.prepareCall(sql1);
							cs1.setInt(1, user.getUserId());
							cs1.setString(2, "Savings Account ");
							cs1.setDouble(3, money);
							cs1.registerOutParameter(4, Types.INTEGER);
							cs1.executeUpdate();
							if(cs1.getInt(4) > 0)
							{
								System.out.println("\nYOUR SAVINGS ACCOUNT HAS BEEN SUCCESSFULLY CREATED.\n");
							}
							else
							{
								System.out.println("\nCOULD NOT CREATE YOUR ACCOUNT.");
							}
							break;
							
					case 3: break;
					default: throw new InvalidOption();
					}
				}
				catch(InvalidOption e)
				{
					System.out.println(e.getMessage());
				}
				if(choice == 1 | choice == 2)
				{
				System.out.println("WOULD YOU LIKE TO CREATE ANOTHER ACCOUNT?\n\n"
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
			
	public static void deleteAccount(User user)
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			try
			{
				System.out.println("\nENTER THE ACCOUNT ID OF THE ACCOUNT YOU WOULD LIKE TO DELETE:");
				int choice;
				choice = sc.nextInt();
				
				String sql5 = "select account_ID from Bank_Accounts";
				Statement stmt = conn.createStatement();
				ResultSet rs5 = stmt.executeQuery(sql5);
				List<Integer> accountList = new ArrayList<Integer>();
				while(rs5.next())
				{
					accountList.add(rs5.getInt("ACCOUNT_ID"));
				}
				
				if(!accountList.contains(choice))
				{
					throw new GhostAccount();
				}
				
				String sql = "select balance from Bank_Accounts where account_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, choice);
				ResultSet rs = ps.executeQuery();
				rs.next();
				double balance = rs.getDouble(1); 
				
				try
				{
					if(balance == 0.0)
					{
						String sql1 = "delete from Bank_Accounts where account_id = ?";
						PreparedStatement ps1 = conn.prepareStatement(sql1);
						ps1.setInt(1, choice);
						ps1.executeUpdate();
						System.out.println("\nACCOUNT SUCCESSFULLY DELETED!");
					}
					else
					{
						throw new ThrowingAwayMoney();
					}
				}
				catch(ThrowingAwayMoney e)
				{
					System.out.println(e.getMessage());
				}
				conn.close();
			}
			catch(GhostAccount g)
			{
				System.out.println(g.getMessage());
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in deleteAccount Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
			
	public static void withdrawDeposit()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			try
			{
				System.out.println("\nENTER THE ACCOUNT ID OF THE ACCOUNT YOU WOULD LIKE TO ACCESS:");
				int accountNum;
				int choice;
				accountNum = sc.nextInt();	
				
				String sql5 = "select account_ID from Bank_Accounts";
				Statement stmt = conn.createStatement();
				ResultSet rs5 = stmt.executeQuery(sql5);
				List<Integer> accountList = new ArrayList<Integer>();
				while(rs5.next())
				{
					accountList.add(rs5.getInt("ACCOUNT_ID"));
				}
				
				if(!accountList.contains(accountNum))
				{
					throw new GhostAccount();
				}
				
				System.out.println("\nWHAT KIND OF TRANSACTION WOULD YOU LIKE TO MAKE?\n\n"
						+ "[1] Withdraw\t\t[2] Deposit\n[3] Return to Accounts Menu");
				
				do
				{
					choice = sc.nextInt();
				
					try 
					{
						switch(choice)
						{
						case 1: System.out.println("\nHOW MUCH WOULD YOU LIKE TO WITHDRAW?");
								double withdraw = sc.nextDouble();
								
								String sql = "select balance from Bank_Accounts where account_id = " + accountNum;
								PreparedStatement ps = conn.prepareStatement(sql);
								ResultSet rs = ps.executeQuery(sql);
								rs.next();
								double balance = rs.getDouble(1);
								
								try
								{
									if(balance >= withdraw)
									{
										double newBalance = balance - withdraw;
										
										if(newBalance > balance)
										{
											System.out.println("\nYOU CANNOT WITHDRAW A NEGATIVE AMOUNT, DEPOSIT INTO THIS ACCOUNT INSTEAD.");
											break;	
										}
										
										String sql2 = "update Bank_Accounts set balance = " + newBalance +
														" where account_id = " + accountNum;
										PreparedStatement ps2 = conn.prepareStatement(sql2);
										ps2.execute();
										System.out.println("\nWITHDRAWAL SUCCESSFUL!");
									}
									else
									{
										throw new OverdraftProtection();
									}
								}
								catch(OverdraftProtection op)
								{
									System.out.println(op.getMessage());
								}
								break;
								
						case 2: System.out.println("\nHOW MUCH WOULD YOU LIKE TO DEPOSIT?");
								double deposit = sc.nextDouble();
								
								String sql3 = "select balance from Bank_Accounts where account_id = " + accountNum;
								PreparedStatement ps1 = conn.prepareStatement(sql3);
								ResultSet rs1 = ps1.executeQuery(sql3);
								rs1.next();
								double balance2 = rs1.getDouble(1);
								
								double newBalance2 = balance2 + deposit;
								
								if(newBalance2 < balance2)
								{
									System.out.println("\nYOU CANNOT DEPOSIT A NEGATIVE AMOUNT, WITHDRAW FROM THIS ACCOUNT INSTEAD.");
									break;
								}
								
								String sql4 = "update Bank_Accounts set balance = " + newBalance2 +
										" where account_id = " + accountNum;
								PreparedStatement ps4 = conn.prepareStatement(sql4);
								ps4.execute();
								
								System.out.println("\nDEPOSIT SUCCESSFUL!");
								break;
							
						case 3: break;
						default: throw new InvalidOption();
						}
					}
					catch(InvalidOption e)
					{
						System.out.println(e.getMessage());
					}
					
					System.out.println("\nWOULD YOU LIKE TO PERFORM ANOTHER TRANSACTION?\n\n"
							+ "[1] Withdraw\t\t[2] Deposit\n[3] Return to Accounts Menu");
				}
				while(choice != 3);
				conn.close();
			}
			catch(GhostAccount g)
			{
				System.out.println(g.getMessage());
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in withdrawDeposit Accounts Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
}