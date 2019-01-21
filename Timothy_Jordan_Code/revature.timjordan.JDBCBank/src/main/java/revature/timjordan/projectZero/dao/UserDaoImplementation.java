package revature.timjordan.projectZero.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import revature.timjordan.projectZero.base.IncorrectPasswordException;
import revature.timjordan.projectZero.models.User;
import revature.timjordan.projectZero.utils.JDBCConnectionUtil;

public class UserDaoImplementation implements UserDao {
	
	
	private static UserDaoImplementation userDao;
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	
	private UserDaoImplementation() {
		
	}
	
	
	public static UserDaoImplementation getUserDao() {
		if(userDao == null) {
			userDao = new UserDaoImplementation();
		}
		
		return userDao;
	}
	
	

	@Override
	public boolean insertUser(User currentUser) {
		// TODO Auto-generated method stub
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into customer values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, currentUser.getId());
			ps.setString(2, currentUser.getFirstName());
			ps.setString(3, currentUser.getLastName());
			ps.setString(4, currentUser.getUserName());
			ps.setString(5, currentUser.getPassword());
			
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
			
		} catch(SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			
		}
		
		
		
		
		return false;
	}
	
	
	
	@Override
	public boolean insertUserProcedure(User currentUser) {
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "Call insert_customer(?,?,?,?)";
			CallableStatement ps= conn.prepareCall(sql);
			
			ps.setString(1, currentUser.getFirstName());
			ps.setString(2, currentUser.getLastName());
			ps.setString(3, currentUser.getUserName());
			ps.setString(4, currentUser.getPassword());
			if(ps.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch(SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			
			
			
		}
		
		
		
		return false;
	}
	
	
	@Override
	public boolean addUser(User currentUser) {
		System.out.println("in adduser");
		System.out.println("Username: " + currentUser.getUserName());
		System.out.println("Password: " + currentUser.getPassword());
		System.out.println("Firstname: " + currentUser.getFirstName());
		System.out.println("Lastname: " + currentUser.getLastName());
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "Call insert_user(?,?,?,?)";
			CallableStatement ps= conn.prepareCall(sql);
			
			
			ps.setString(1, currentUser.getUserName());
			ps.setString(2, currentUser.getPassword());
			ps.setString(3, currentUser.getFirstName());
			ps.setString(4, currentUser.getLastName());
			if(ps.executeUpdate() == 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch(SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			
			
			
		}
		return false;
	}
	
	public boolean modifyUser(String columnName, String newValue, int user_ID) {
		System.out.println("column name: " + columnName);
		System.out.println("newValue " + newValue);
		System.out.println("USERID: " + user_ID);
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "update usertable set " + columnName + " = '" + newValue + "' where USER_ID = '" + user_ID + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
			
			
			
		} catch(SQLException e) {
			
		}
		
		
		
		
		
		
		return false;
	}
	
	public boolean deleteUser(User currentUser) {
		int user_ID = currentUser.getId();
		System.out.println("UserID: " + user_ID);
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "delete from usertable where USER_ID = '" + user_ID + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
			
			
			
		} catch(SQLException e) {
			
		}
		return false;
	}
	
	@Override
	public boolean isUser(User currentUser) {
		//System.out.println(currentUser.getUserName());
		String userName = currentUser.getUserName();
		//System.out.println("UserName: " + userName);
		//User temp = new User();
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select FIRST_NAME from usertable where USER_NAME = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			//results.findColumn("C_USERNAME");
			//System.out.println(results.next());
			if(results.next()) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean isPassword(User currentUser) {
		String userName = currentUser.getUserName();
		String password = currentUser.getPassword();
		User tempUser = null;
		//System.out.println("Username: " + userName + "Password: " + password);
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from usertable where USER_NAME = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results  = stmt.executeQuery(sql);
			while(results.next()) {
				//log.info("inside the while loop for getUser");
				tempUser =  new User(
						results.getInt("USER_ID"),
						results.getString("FIRST_NAME"),
						results.getString("LAST_NAME"),
						results.getString("USER_NAME"),
						results.getString("USER_PASSWORD")
						
						
						);
			}
			
			
			//System.out.println("USER" + tempUser);
			//System.out.println(tempUser.getPassword());
			
		} catch(SQLException e) {
			
		}
		//System.out.println(tempUser.getPassword());
		//System.out.println("input: " + password);
		if(tempUser.getPassword().equals(password)) {
			//System.out.println("Logging In!");
			return true;
		} else {
			//throw an exception here
			//System.out.println("Incorret Password");
			
			try {
				throw new IncorrectPasswordException("Incorrect Password Exception");
			} catch (IncorrectPasswordException e) {
				log.error("Incorrect Password Exception");
				return false;
			}
			
		}
		
	}
	
	@Override
	public int getUserId(User currentUser) {
		String userName = currentUser.getUserName();
		int user_ID;
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			String sql = "select USER_ID from usertable where USER_NAME = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//System.out.println(results.getInt("USER_ID"));
				return results.getInt("USER_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		try(Connection conn = JDBCConnectionUtil.getConnection()){
			//bad practice- never hard code values in a method
			//how to avoid - using prepared statement - ps supports pparameterized sql
			String sql = "select * from usertable where USER_NAME = '" + userName + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//log.info("inside the while loop for getUser");
				return new User(
						results.getInt("USER_ID"),
						results.getString("USER_NAME"),
						results.getString("USER_PASSWORD"),
						results.getString("FIRST_NAME"),
						results.getString("LAST_NAME")
						
						
						);
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			//log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from usertable";
			
			//log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			//log.info("viewing the results");
			List<User> allUsers = new ArrayList<>();
			while(results.next()) {
				allUsers.add(new User(
							results.getInt("USER_ID"),
							results.getString("FIRST_NAME"),
							results.getString("LAST_NAME"),
							results.getString("USER_NAME"),
							results.getString("USER_PASSWORD")
						
						));
				
			}
			return allUsers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return new ArrayList<>();
	}

}
