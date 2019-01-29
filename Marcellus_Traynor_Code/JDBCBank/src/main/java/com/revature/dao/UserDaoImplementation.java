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
import com.revature.exceptions.InvalidCreds;
import com.revature.exceptions.InvalidOption;
import com.revature.exceptions.UsernameCopycat;
import com.revature.models.User;
import com.revature.services.AccountsService;
import com.revature.util.JDBCConnectionUtil;

public class UserDaoImplementation implements UserDAO 
{
	private static UserDaoImplementation userDao;
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	public UserDaoImplementation() {}
	
	
	public static UserDaoImplementation getUserDao()
	{
		if(userDao == null)
		{
			userDao = new UserDaoImplementation();
		}
		return userDao;
	}
	
	@Override
	public void welcome() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the JDBCBanking portal!\n");
		System.out.println("[1] Existing User Login\n[2] New User Registration\n[3] Exit Portal");
		JDBCConnectionUtil.standardConnection();
		
		int choice;
		
		try
		{
			do
			{
				choice = sc.nextInt();
				
				switch(choice)
				{
				case 1: login();
						break;
				case 2: registerUser(new User("", "", "", ""));
						break;
				case 3: System.out.println("\nThank you for banking with JDBCBank. Goodbye :)");
						sc.close();
						System.exit(0);
				default: throw new InvalidOption();
				}
				System.out.println("[1] Existing User Login\n[2] New User Registration\n[3] Exit Portal");
			}
			while(choice != 3);
		}
		catch(InvalidOption e)
		{
			System.out.println(e.getMessage());
			welcome();
		}
	}
		
	@Override
	public void registerUser(User user) 
	{	
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection()) 
		{
			try
			{
				System.out.println("ENTER FIRST NAME:");
				String firstName = sc.nextLine();
				
				System.out.println("ENTER LAST NAME:");
				String lastName = sc.nextLine();
				
				System.out.println("ENTER USERNAME:");
				String userName = sc.nextLine();
				
				String sql = "select user_name from User_Details";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				List<String> nameList = new ArrayList<String>();
				while(rs.next())
				{
					nameList.add(rs.getString("USER_NAME"));
				}
				
				if(nameList.contains(userName))
				{
					throw new UsernameCopycat();
				}
				
				System.out.println("ENTER PASSWORD:");
				String password = sc.nextLine();
				
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setUserName(userName);
				user.setPassword(password);
			
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
					connectUser(user);
					System.out.println("\nTHANK YOU FOR REGISTERING!\n");
					conn.close();
				}
				else
				{
					throw new SQLException();
				}
			}
			catch(UsernameCopycat e)
			{
				System.out.println(e.getMessage());
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in register user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
	
	@Override
	public void login()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER YOUR USERNAME:");
		String userName = sc.nextLine();
		
		System.out.println("ENTER YOUR PASSWORD:");
		String password = sc.nextLine();
		
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
			
			try
			{
				if(nameList.contains(userName) & passList.contains(password))
				{
					JDBCConnectionUtil.setConnection(userName, password);
					User user = getUser(userName);
					
					if(user.getUserId() == 100)
					{
						superMenu();
					}
					else
					{
						AccountsService.accountsMenu(user);
					}
				}
				else
				{
					throw new InvalidCreds();
				}
			}
			catch(InvalidCreds ic)
			{
				System.out.println(ic.getMessage());
				conn.close();
			}
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in login user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
	
	@Override
	public void connectUser(User user)
	{
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			log.info("Creating User connection");
			String sql = "CREATE USER " + user.getUserName() + " IDENTIFIED BY " + user.getPassword();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			
			log.info("Granting accesses");
			String sql1 = "select user_ID from User_Details where user_name = ?";
			PreparedStatement pss = conn.prepareStatement(sql1);
			pss.setString(1, user.getUserName());
			ResultSet rs = pss.executeQuery();
			rs.next();
			
			if(rs.getInt(1) == 100)
			{
				String superUser = "GRANT dba TO " + user.getUserName() + " WITH ADMIN OPTION";
				PreparedStatement ps1 = conn.prepareStatement(superUser);
				ps1.execute();
			}
			else
			{
				String userN = user.getUserName();
				
				String grantSession = "GRANT create session TO " + userN;
				PreparedStatement ps2 = conn.prepareStatement(grantSession);
				ps2.execute();
				
				String grantModify = "GRANT select, insert, delete, update on Bank_Accounts TO " + userN;
				PreparedStatement ps3 = conn.prepareStatement(grantModify);
				ps3.execute();
				
				String grantSequence = "GRANT select on account_seq TO " + userN;
				PreparedStatement ps4 = conn.prepareStatement(grantSequence);
				ps4.execute();
				
				String grantModify2 = "GRANT select, insert, delete, update on User_Details TO " + userN;
				PreparedStatement ps5 = conn.prepareStatement(grantModify2);
				ps5.execute();
				
				String grantProcedure = "GRANT execute on register_user TO " + userN;
				PreparedStatement ps6 = conn.prepareStatement(grantProcedure);
				ps6.execute();
				
				String grantProcedure2 = "GRANT execute on create_account TO " + userN;
				PreparedStatement ps7 = conn.prepareStatement(grantProcedure2);
				ps7.execute();
			}
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in connectUser user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public User getUser(String userName) 
	{
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			String sql = "select * from User_Details where user_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet results = ps.executeQuery();
			while(results.next())
			{
				log.info("inside the while loop for getCustomer");
				return new User(
						results.getInt("USER_ID"),
						results.getString("FIRST_NAME"),
						results.getString("LAST_NAME"),
						results.getString("USER_NAME"),
						results.getString("USER_PASSWORD"));
			}
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in getUser user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return null;
	}
	
	@Override
	public void superMenu()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			System.out.println("\nWELCOME TO THE SUPER USER MENU. WHAT WOULD YOU LIKE TO DO?\n");
			System.out.println("UserID\tFirst_Name\tLast_Name\tUsername\tPassword");
			System.out.println("******************************************************************");
			
			String sql = "select * from User_Details where User_ID <> 100";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t\t");
				System.out.print(rs.getString(3) + "\t\t");
				System.out.print(rs.getString(4) + "         ");
				System.out.print(rs.getString(5) + "\n\n");
			}
			
			System.out.println("[1] Create a new user\t\t\t[3] Delete a user");
			System.out.println("[2] Update a user's password\t\t[4] Logout");
				
			int choice;
			
			do
			{
				choice = sc.nextInt();
				sc.nextLine();
				try
				{
					switch(choice)
					{
					case 1: registerUser(new User("", "", "", ""));
							break;
					case 2: updatePassword();
							break;
					case 3: deleteUser();
							break;
					case 4: System.out.println("\nHave a super day, Super User. Goodbye :)\n");
							welcome();
					default: throw new InvalidOption();	
					}
				}
				catch(InvalidOption e)
				{
					System.out.println(e.getMessage());
				}
				
				System.out.println("\nWELCOME TO THE SUPER USER MENU. WHAT WOULD YOU LIKE TO DO?\n");
				System.out.println("UserID\tFirst_Name\tLast_Name\tUsername\tPassword");
				System.out.println("******************************************************************");
				
				String sql1 = "select * from User_Details where User_ID <> 100";
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(sql1);
				while(rs1.next())
				{
					System.out.print(rs1.getInt(1) + "\t");
					System.out.print(rs1.getString(2) + "\t\t");
					System.out.print(rs1.getString(3) + "\t\t");
					System.out.print(rs1.getString(4) + "         ");
					System.out.print(rs1.getString(5) + "\n\n");
				}
				
				System.out.println("[1] Create a new user\t\t\t[3] Delete a user");
				System.out.println("[2] Update a user's password\t\t[4] Logout");
			}
			while(choice != 4);
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in superMenu User Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@Override
	public void updatePassword()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			try
			{	
				System.out.println("\nENTER THE USERID OF THE USER YOU WOULD LIKE TO UPDATE:");
				int userID = sc.nextInt();
				sc.nextLine();
				
				String sql3 = "select user_ID from User_Details";
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(sql3);
				List<Integer> IDList = new ArrayList<Integer>();
				while(rs1.next())
				{
					IDList.add(rs1.getInt("USER_ID"));
				}
				
				if(!IDList.contains(userID))
				{
					throw new GhostAccount();
				}
				
				System.out.println("\nENTER THIS USER'S NEW PASSWORD:");
				String newPass = sc.nextLine();
				
				String sql4 = "SELECT User_Password FROM User_Details WHERE User_ID = " + userID; 
				PreparedStatement ps2 = conn.prepareStatement(sql4);
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				String oldPass = rs2.getString(1);
				
				String sql = "UPDATE User_Details SET User_Password = ? WHERE User_ID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, newPass);
				ps.setInt(2, userID);
				ps.executeUpdate();
				
				String sql1 = "SELECT User_Name FROM User_details WHERE User_ID = ?";
				PreparedStatement ps3 = conn.prepareStatement(sql1);
				ps3.setInt(1, userID);
				ResultSet rs = ps3.executeQuery();
				rs.next();
				String uName = rs.getString(1);
				
				String sql2 = "ALTER USER " + uName + " IDENTIFIED BY " + newPass + " REPLACE " + oldPass;
				PreparedStatement ps1 = conn.prepareStatement(sql2);
				ps1.execute();
			
				System.out.println("\nPASSWORD UPDATED SUCCESSFULLY!\n");
			}
			catch(GhostAccount e)
			{
				System.out.println(e.getMessage());
			}
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in updatePassword User Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
	
	@Override
	public void deleteUser()
	{
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = JDBCConnectionUtil.getConnection())
		{
			try
			{
				System.out.println("ENTER THE USERID OF THE USER YOU WOULD LIKE TO DELETE:");
				int userID = sc.nextInt();
				sc.nextLine();
				
				System.out.println("ENTER THE USERNAME OF THE USER YOU WOULD LIKE TO DELETE:");
				String userN = sc.nextLine();
				
				String sql3 = "select user_ID from User_Details";
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(sql3);
				List<Integer> IDList = new ArrayList<Integer>();
				while(rs1.next())
				{
					IDList.add(rs1.getInt("USER_ID"));
				}
				
				if(!IDList.contains(userID))
				{
					throw new GhostAccount();
				}
				
				String sql4 = "select user_name from User_Details";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql4);
				List<String> nameList = new ArrayList<String>();
				while(rs.next())
				{
					nameList.add(rs.getString("USER_NAME"));
				}
				
				if(!nameList.contains(userN))
				{
					throw new GhostAccount();
				}
				
				String sql = "delete from Bank_Accounts where U_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userID);
				ps.executeUpdate();
				
				String sql1 = "delete from User_Details where User_ID = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, userID);
				ps1.executeUpdate();
				
				String sql2 = "drop user " + userN;
				Statement stmt2 = conn.createStatement();
				stmt2.execute(sql2);
				
				System.out.println("\nUSER AND ALL ASSOCIATED ACCOUNTS HAVE BEEN SUCCESSFULLY DELETED.");
			}
			catch(GhostAccount e)
			{
				System.out.println(e.getMessage());
			}
			conn.close();
		} catch (SQLException e) 
		{
			log.error("error occured in catch block in deleteUser User Dao Implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			sc.close();
		}
	}
}