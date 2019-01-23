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
import com.revature.utils.JDBCConnectUtil;


//Dao Class implements dao interface
//Dao class retreives data from database
//in our example connection to the database will be handled by utils

public class UserDaoImplementation implements UserDAO{

	private static UserDaoImplementation userDao;
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
	public boolean createConnection(User user) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "create user "+ user.getUsername() +" identified by "+ user.getPassword();
			String sqlPermissions = "grant create session to " + user.getUsername();
			String sqlPermissions1 = "grant select on bank_users to " + user.getUsername();
			String sqlPermissions2 = "grant select, insert, update, delete on bank_accounts to " + user.getUsername();
			String sqlPermissions3 = "grant select on new_user_id to " + user.getUsername();
			String sqlPermissions4 = "grant select on new_account_id to " + user.getUsername();
			String sqlPermissions5 = "grant select on new_transaction_id to " + user.getUsername();
			String sqlPermissions6 = "grant select, insert, delete on account_transactions to " + user.getUsername();
			Statement ps = conn.createStatement();
		
			
			ps.executeQuery(sql);
			ps.executeQuery(sqlPermissions);
			ps.executeQuery(sqlPermissions1);
			ps.executeQuery(sqlPermissions2);
			ps.executeQuery(sqlPermissions3);
			ps.executeQuery(sqlPermissions4);
			ps.executeQuery(sqlPermissions5);
			ps.executeQuery(sqlPermissions6);
				return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
	
	@Override
	public boolean insertUser(User user) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "insert into bank_users values(new_user_id.nextval,?,?,?)";
			String output = "";
			CallableStatement cs = conn.prepareCall("{? = call encrypt_password(?,?)}");	
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			output = cs.getString(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, output);		
			ps.setInt(3, user.getSuperuser());
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
	public User getUser(String username) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from bank_users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet results = ps.executeQuery();
			
			while (results.next()) {
				return new User(results.getInt("user_id"), results.getString("username"), results.getString("password"), results.getInt("superuser"));
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new User();
	}

	@Override
	public List<User> getAllUsers() {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from bank_users order by user_id");
			List<User> allUsers = new ArrayList<>();
			while(results.next()) {				
				allUsers.add(new User(results.getInt("user_id"), results.getString("username"), results.getString("password"), results.getInt("superuser")));
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
		try (Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select username from bank_users where user_id ='"+ userID +"'");	
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
	public boolean userExists(String username) {
		try (Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select username from bank_users where username ='"+ username +"'");	

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
		try (Connection conn = JDBCConnectUtil.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select password from bank_users where username ='"+ username +"'");	
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
		}catch(SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public boolean deleteUser(int userID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			CallableStatement cs = conn.prepareCall("call delete_user(?)");	
			cs.setInt(1, userID);
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public User getUser(int userID) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "select * from bank_users where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				return new User(results.getInt("user_id"), results.getString("username"), results.getString("password"), results.getInt("superuser"));
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return new User();
	}

	@Override
	public boolean editUser(String oldUsername, String oldPassword, User user) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			//using prepared statement with parameterized statements: ?
			String sql = "update bank_users set username = ?, password = ?, superuser = ? where user_id = ?";
			String encrypt = "{? = call encrypt_password(?,?)}";
			CallableStatement cs = conn.prepareCall(encrypt);
			
			cs.setString(2, user.getUsername());
			cs.setString(3, user.getPassword());
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.execute();
			String output = cs.getString(1);
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, output);
			ps.setInt(3, user.getSuperuser());
			ps.setInt(4, user.getUserID());
			
			ResultSet results = ps.executeQuery();

		return true;				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUserConnection(String username) {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			//never hardcode values in a method
			String sql = "drop user " + username;
			Statement ps = conn.createStatement();
						
			ps.executeQuery(sql);
			return true;				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteAllUsers() {
		try(Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "delete from bank_users where username != 'super'";
			PreparedStatement ps = conn.prepareStatement(sql);	
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}
}
