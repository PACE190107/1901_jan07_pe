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

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utils.JDBCConnectionUtil;

public class UserDaoImp implements UserDao {

	private static UserDaoImp userDao;
	final static Logger log = Logger.getLogger(UserDaoImp.class);
	private UserDaoImp() {
	}
	
	public static UserDaoImp getUserDao() {
		if (userDao == null) {
			userDao = new UserDaoImp();
		}
		return userDao;
	}
	
	@Override
	public boolean insertUser(User user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into user_table values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_id());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getUserName());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getSuperuser());
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	@Override
	public boolean deleteUserProcedure(User user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call DELETE_USER(?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, user.getUser_id());
			if(ps.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	//stored procedure in SQL
	public boolean insertUserProcedure(User user) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call ADD_USER(?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			//ps.executeUpdate();
			if(ps.executeUpdate() >= 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert user dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	public User getUser() {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
		    String sql = "select * from user_table where FIRSTNAME = 'Kenneth'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				log.info("inside the while loop for getUser");
				return new User(
						results.getInt("USER_ID"), 
						results.getString("FIRSTNAME"),  
						results.getString("LASTNAME"),
						results.getString("USERNAME"),
						results.getString("PASSW0RD"), 
						results.getInt("SUPERUSER")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from user_table";
			
			log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			log.info("viewing the results");
			List<User> allUsers = new ArrayList<>();
			while (results.next()) {
				allUsers.add(new User(
						results.getInt("USER_ID"), 
						results.getString("FIRSTNAME"), 
						results.getString("LASTNAME"),
						results.getString("USERNAME"),
						results.getString("PASSW0RD"),
						results.getInt("SUPERUSER"))
						);
			}
			return allUsers;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

}
