package com.revautre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


import com.revature.models.User;
import com.revature.utils.JDBCConnectionUtil;



public class UserDaoImplementation implements UserDao {
	
	
private static UserDaoImplementation userDao;
	
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	
	private UserDaoImplementation() {
	}
	
	public static UserDaoImplementation getCustDao() {
		if (userDao == null) {
			userDao = new UserDaoImplementation();
		}
		return userDao;
	}
	
	
	///works but needs logic
	//EmployeeLoginMEthod//////////////////////////////////////////////////////////////////////////////////////
	public User goodLogin(String email, String password) {
		
		System.out.println(email);
		System.out.println(password);
		
try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			User tempUser = null;
			
			String sqlUsername = "SELECT * FROM USERS where EMAIL = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sqlUsername);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet resultSet = ps.executeQuery();
			System.out.println(resultSet);
			//System.out.println(resultSet.next());
			
			
			
			
			
			while(resultSet.next()) {
				System.out.println("in while");
				tempUser = new User(
						resultSet.getInt("E_ID"),
						resultSet.getString("FIRST_NAME"),
						resultSet.getString("LAST_NAME"),
						resultSet.getString("EMAIL"),
						resultSet.getString("PASSWORD"),
						resultSet.getInt("manager")
						
						
						
						
						);
			
			}
				
				
			
			
		
			System.out.println("tempUser in goodLogin: " + tempUser);
			if(tempUser == null) {
				
				return null;
			} else {
				System.out.println("in else");
				return tempUser;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	//not tested yet
	//managerLoginMethod///////////////////////////////////////////////////////////////////////
		
public User managerLogin(String email, String password) {
	
	System.out.println(email);
	System.out.println(password);
	
try ( Connection conn = JDBCConnectionUtil.getConnection() ){
		User tempUser = null;
		
		String sqlUsername = "SELECT * FROM USERS where EMAIL = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(sqlUsername);
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet resultSet = ps.executeQuery();
		System.out.println(resultSet);
		
		
		
		
		
		
		while(resultSet.next()) {
			System.out.println("in while");
			tempUser = new User(
					resultSet.getInt("E_ID"),
					resultSet.getString("FIRST_NAME"),
					resultSet.getString("LAST_NAME"),
					resultSet.getString("EMAIL"),
					resultSet.getString("PASSWORD"),
					resultSet.getInt("manager")
					
					
					
					
					);
		
		}
			
		System.out.println("tempUser in managerLogin: " + tempUser);
		if(tempUser == null) {
			
			return null;
		} else if
			//System.out.println("in else");
			(resultSet.getInt("manager")==1);
			
			return tempUser;
		}
		
	catch (SQLException e) {
		e.printStackTrace();
		return null;
	} 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
