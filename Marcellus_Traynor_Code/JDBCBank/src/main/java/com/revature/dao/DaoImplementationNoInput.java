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
import com.revature.exceptions.OverdraftProtection;
import com.revature.exceptions.ThrowingAwayMoney;
import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class DaoImplementationNoInput 
{
	private static UserDaoImplementation userDao;
	public void UserDaoImplementation() {}
		
	public static UserDaoImplementation getUserDao()
		{
			if(userDao == null)
			{
				userDao = new UserDaoImplementation();
			}
			return userDao;
		}
		
	public boolean registerUser(User user) 
		{	
			try(Connection conn = JDBCConnectionUtil.getConnection()) 
			{
					String sql1 = "call register_user(?,?,?,?,?)";
					CallableStatement cs = conn.prepareCall(sql1);
					cs.setString(1, user.getFirstName());
					cs.setString(2, user.getLastName());
					cs.setString(3, user.getUserName());
					cs.setString(4, user.getPassword());
					cs.registerOutParameter(5, Types.INTEGER);
					cs.executeUpdate();
					if(cs.getInt(5) > 0)
					{
						return true;
					}
					else
					{
						throw new SQLException();
					}
			} catch (SQLException e) 
			{
			}
			return true;
		}
		
	public boolean login(User user)
		{
			try(Connection conn = JDBCConnectionUtil.getConnection())
			{
				String sql = "select user_name from User_Details";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				List<String> nameList = new ArrayList<String>();
				while(rs.next())
				{
					nameList.add(rs.getString("USER_NAME"));
				}
				
				String sql1 = "select user_password from User_Details";
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(sql1);
				List<String> passList = new ArrayList<String>();
				while(rs1.next())
				{
					passList.add(rs1.getString("USER_PASSWORD"));
				}
				
				if(nameList.contains(user.getUserName()) & passList.contains(user.getPassword()))
				{		
					return true;
				}
			} catch (SQLException e) 
			{
			}
			return true;
		}
		
	public boolean updatePassword(User user, String newP)
		{			
			try(Connection conn = JDBCConnectionUtil.getConnection())
			{		
				String sql = "UPDATE User_Details SET User_Password = ? WHERE User_ID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, newP);
				ps.setInt(2, user.getUserId());
				int test = ps.executeUpdate();
				
				if(test > 0)
				{
					return true;
				}
					
				conn.close();
			} catch (SQLException e) 
			{
			}
			return true;
		}
		
	public boolean deleteUser(User user)
		{
			try(Connection conn = JDBCConnectionUtil.getConnection())
			{						
				String sql = "delete from Bank_Accounts where U_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, user.getUserId());
				int test = ps.executeUpdate();
					
				String sql1 = "delete from User_Details where User_ID = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, user.getUserId());
				int test2 = ps1.executeUpdate();	
				
				if(test > 0 & test2 > 0)
				{
					return true;
				}
				
				conn.close();
			} catch (SQLException e) 
			{
			}
			return true;
		}
	
	private static AccountsDaoImplementation accountsDao;
	public void AccountsDaoImplementation() {}
	
	public static AccountsDaoImplementation getAccountsDao()
	{
		if(accountsDao == null)
		{
			accountsDao = new AccountsDaoImplementation();
		}
		return accountsDao;
	}
			
	public static boolean newAccount(User user, int choice, double money)
	{
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
					switch(choice)
					{
					case 1: String sql = "call create_account(?,?,?,?)";
							CallableStatement cs = conn.prepareCall(sql);
							cs.setInt(1, user.getUserId());
							cs.setString(2, "Checking Account");
							cs.setDouble(3, money);
							cs.registerOutParameter(4, Types.INTEGER);
							cs.executeUpdate();
							if(cs.getInt(4) > 0)
							{
								return true;
							}
							break;
							
					case 2: String sql1 = "call create_account(?,?,?,?)";
							CallableStatement cs1 = conn.prepareCall(sql1);
							cs1.setInt(1, user.getUserId());
							cs1.setString(2, "Savings Account ");
							cs1.setDouble(3, money);
							cs1.registerOutParameter(4, Types.INTEGER);
							cs1.executeUpdate();
							if(cs1.getInt(4) > 0)
							{
								return true;
							}
							break;		
				}
					conn.close();
		} catch (SQLException e) 
		{
		}
		return true;
	}
			
	public static boolean deleteAccount(double balance)
	{	
		if(balance == 0.0)
		{
			return true;
		}
		else
		{
			throw new ThrowingAwayMoney();
		}
	}
			
	public static boolean withdrawDeposit(int choice, double money, double balance)
	{			
		switch(choice)
		{
		case 1: if(balance >= money)
				{
					return true;
				}
				else
				{
					throw new OverdraftProtection();
				}
								
		case 2: double newbalance = money + balance;
				if(newbalance > balance)
				{
					return true;
				}
		}
		return true;
	}
}