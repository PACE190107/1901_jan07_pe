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
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.JDBCConnectionUtil;

public class UserDaoImplementation implements UserDAO 
{
	private static UserDaoImplementation userDao;
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	private UserDaoImplementation() {}
	
	
	public static UserDaoImplementation getUserDao()
	{
		if(userDao == null)
		{
			userDao = new UserDaoImplementation();
		}
		return userDao;
	}
	
	public void welcome()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the JDBCBanking portal!");
		System.out.println("[1] Existing User Login\n[2] New User Registration\n[3] Exit Portal");
		
		int choice;
		
		do
		{
			choice = sc.nextInt();
			
			switch(choice)
			{
			case 1: login();
					break;
			case 2: registerUser(new User("", "", "", ""));
					break;
			case 3: System.exit(0);
			default: System.out.println("Invalid input, try again.");
			}
			System.out.println("[1] Existing User Login\n[2] New User Registration\n[3] Exit Portal");
		}
		while(choice != 3);
		
		sc.close();
	}
		
	@Override
	public void registerUser(User user) 
	{	
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection()) 
		{
			System.out.println("Enter first name:");
			String firstName = sc.nextLine();
			
			System.out.println("Enter last name:");
			String lastName = sc.nextLine();
			
			System.out.println("Enter your username:");
			String userName = sc.nextLine();
			
			System.out.println("Enter password:");
			String password = sc.nextLine();
			
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setPassword(password);
			
			String sql = "call register_user(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUserName());
			cs.setString(4, user.getPassword());
			cs.registerOutParameter(5, Types.INTEGER);
			cs.executeUpdate();
			if(cs.getInt(5) > 0)
			{
				//sc.close();
				System.out.println("Thank you for registering!");
			}
			else
			{
				//sc.close();
				throw new SQLException();
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in register user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	

	public void login()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("Enter your username:");
			String userName = sc.nextLine();
			
			System.out.println("Enter your password:");
			String password = sc.nextLine();
			
			String sql1 = "select User_name from User_Details";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery(sql1);
			List<String> nameList = new ArrayList<String>();
			while(rs1.next())
			{
				nameList.add(rs1.getString("USER_NAME"));
			}
			
			String sql2 = "select user_password from User_Details";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery(sql2);
			List<String> passList = new ArrayList<String>();
			while(rs2.next())
			{
				passList.add(rs2.getString("USER_PASSWORD"));
			}
			
			
			if(nameList.contains(userName) & passList.contains(password))
			{
				userMenu();
			}
			else
			{
				System.out.println("Invalid login credentials!\n[1] Check your Information and Try Again\n"
						+ "[2] New User Registration\n[3] Exit Portal");
				
				int choice;
				
				do
				{
					choice = sc.nextInt();
					
					switch(choice)
					{
					case 1: login();
							break;
					case 2: registerUser(new User("", "", "", ""));
							break;
					case 3: System.exit(0);
					default: System.out.println("Invalid input, try again.");
					}
				}
				while(choice != 3);
				
				sc.close();
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in login user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}

	public void userMenu()
	{
		System.out.println("Account Menu");
	}
	
//	@Override
//	public User getUser() 
//	{
//		try(Connection conn = JDBCConnectionUtil.getConnection())
//		{
//			//bad practice - never hard code values in a method
//			//avoid this by using prepared statements - PS support parameterized sql
//			String sql = "select * from User";
//			Statement stmt = conn.createStatement();
//			ResultSet results = stmt.executeQuery(sql);
//			while(results.next())
//			{
//				log.info("inside the while loop for getCustomer");
//				return new User(
//						results.getInt("C_ID"),
//						results.getString("C_FIRSTNAME"),
//						results.getString("C_LASTNAME"),
//						results.getString("C_USERNAME"),
//						results.getString("C_PASSWORD"));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}