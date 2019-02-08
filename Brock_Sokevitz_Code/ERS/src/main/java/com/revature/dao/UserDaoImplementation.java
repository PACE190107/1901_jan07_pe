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

import com.revature.model.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoImplementation implements UserDao {

	private static UserDaoImplementation userDao;
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	
	private UserDaoImplementation() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserDaoImplementation getUserDao() {
		
		if(userDao == null)
				userDao = new UserDaoImplementation();
		
		return userDao;
	}
	
	@Override
	public boolean insertUser(User user) {
		
		Connection conn = null;
		conn = cu.getConnection();
		log.info(user);;
		try{
			String sql = "insert into users values(?,?,?,?)";
			String output = "";
			CallableStatement cs = conn.prepareCall("{? = call encrypt_password(?,?)}");	
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			output = cs.getString(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, output);		
			ps.setInt(4, user.getSuperuser());
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public boolean createConnection(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userExists(String username) {
		Connection conn = null;
		conn = cu.getConnection();
		try {
			String sql = "select username from users where username = ?";			
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()) {
			return true;
			}
		}catch(SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		
		
		return false;
	}

	@Override
	public boolean verifyPassword(String username, String password) {

			Connection conn = null;
			conn = cu.getConnection();
			String sql = "select password from users where username = ?";
			
			try {
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				
				String output = "";
				CallableStatement cs = conn.prepareCall("{? = call encrypt_password(?,?)}");	
				cs.setString(2, username);
				cs.setString(3, password);
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.execute();
				output = cs.getString(1);
				if(rs.next() && rs.getString(1).equals(output)) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		return false;
	}

	@Override
	public User getUser(String username) {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet results = ps.executeQuery();
			
			if(results.next()) {
				return new User(results.getString("username"), results.getString("email"), results.getString("password"), results.getInt("superuser"));
				
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return new User();
	}

	@Override
	public boolean deleteUser(String username) {
		Connection conn = null;
		conn = cu.getConnection();
		try{
			CallableStatement cs = conn.prepareCall("call delete_user(?)");	
			cs.setString(1, username);
			boolean rs = cs.execute();
				return rs;
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from users order by username");
			List<User> allUsers = new ArrayList<>();
			while(results.next()) {				
				allUsers.add(new User(results.getString("username"), results.getString("email"), "n/a", results.getInt("superuser")));
			}
			return allUsers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return new ArrayList<>();
	}

	@Override
	public boolean userExists(int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User user, String oldUsername) {

		
		Connection conn = null;
		conn = cu.getConnection();
		
		try{
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update users set username = ?, email=?, password=? where username=?";
			String output = "";
			CallableStatement cs = conn.prepareCall("{? = call encrypt_password(?,?)}");	
			log.info("new user" + user.getUsername());
			log.info("new pass" + user.getPassword());
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			output = cs.getString(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, output);
			ps.setString(4, oldUsername);
			
			ResultSet results = ps.executeQuery();
			if(results.next()) {
				return user;				
			}	
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}


	@Override
	public boolean deleteUserConnection(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllUsers() {
		// TODO Auto-generated method stub
		return false;
	}

}
