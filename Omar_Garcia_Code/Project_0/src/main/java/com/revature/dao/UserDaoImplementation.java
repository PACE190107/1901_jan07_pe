package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class UserDaoImplementation implements UserDao{
	
	private static UserDaoImplementation userdi;
	final static Logger log =Logger.getLogger(UserDaoImplementation.class);

	private UserDaoImplementation() {
		
	}
	
	public static UserDaoImplementation getUserDao(){
		if(userdi == null) {
			userdi = new UserDaoImplementation();
		}
		return userdi;
	}
	
	public User login(String username, String password) throws SQLException{
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USERS where U_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return new User(result.getInt("U_ID"), 
						result.getString("U_FIRST"),
						result.getString("U_LAST"), 
						result.getString("U_USERNAME"), 
						result.getString("U_PASSWORD"),
						result.getInt("SUPERUSER"));
			}
		}
		return new User();	
	}
	
	public User register(String first, String last, String username, String password) throws SQLException {
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "CALL INSERT_USER(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, first);
			cs.setString(2, last);
			cs.setString(3, username);
			cs.setString(4, password);
			cs.execute();
			
			sql = "SELECT * FROM USERS where U_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return new User(result.getInt("U_ID"), 
						result.getString("U_FIRST"),
						result.getString("U_LAST"), 
						result.getString("U_USERNAME"), 
						result.getString("U_PASSWORD"));
			}
		}
		return new User();	
	}
	
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean insertUserPrecedure(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
