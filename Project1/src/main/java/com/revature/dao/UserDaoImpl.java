package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.JDBSCConnectionUtil;

public class UserDaoImpl implements UserDao{
	
	private static UserDaoImpl usDa;
	
	private UserDaoImpl() {
		
	}
	
	public static UserDaoImpl getUsDa() {
		if(usDa == null) {
			usDa = new UserDaoImpl();
		}
		
		return usDa;
	}

	@Override
	public User getUser(String username) {
		System.out.println("getUser is called properly");
		System.out.println("username" + username);
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "Select * from ACCOUNT_ID where USERNAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				
				return new User(
						results.getInt("USER_ID"),
						results.getString("USERNAME"),
						results.getString("FIRSTNAME"),
						results.getString("LASTNAME"),
						results.getString("EMAIL"),
						results.getString("PASSWORD"),
						results.getInt("IS_MANAGER"),
						results.getInt("USER_REIM")
								);
			}	
		} catch (SQLException e) {
			e.getSQLState();
			e.getErrorCode();
			e.printStackTrace();
		}
		System.out.println("returned null in getUser call");
		return null;
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		System.out.println("getAllUsers is called properly");
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "select * from account_id where IS_MANAGER = 0";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			
			ResultSet results = stmt.executeQuery();
			
			List<User> allUsers = new ArrayList<>();
			while(results.next()) {
				allUsers.add(new User(
						results.getInt("USER_ID"),
						results.getString("USERNAME"),
						results.getString("FIRSTNAME"),
						results.getString("LASTNAME"),
						results.getString("EMAIL"),
						results.getString("PASSWORD"),
						results.getInt("IS_MANAGER"),
						results.getInt("USER_REIM"))
						);
			}
			return allUsers;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
		
	}

}
