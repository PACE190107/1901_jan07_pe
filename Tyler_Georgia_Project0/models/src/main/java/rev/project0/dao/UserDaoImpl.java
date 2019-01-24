package rev.project0.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rev.project0.models.User;
import rev.project0.util.JDBCConnectionUtil;

public class UserDaoImpl implements UserDao{

	
	private static UserDaoImpl userDao;
	
	private UserDaoImpl() {
		
	}
	
	public static UserDaoImpl getUserDao() {
		if(userDao == null) {
			userDao = new UserDaoImpl();
		}
		
		return userDao;
	}
	
	public boolean createUser(User newUser) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call insert_user (?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, newUser.getPassword());
			ps.setString(2, newUser.getUserName());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	public User getUser(String userName, String password) {
		ResultSet queryResult = null;
		User qUser = null;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from bank_user where username =\'" +userName+ "\' AND user_password =\'" + password+"\'" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			queryResult = ps.executeQuery();
	 
			if(queryResult.next()) {
				String username = queryResult.getString("username");
				int userId = queryResult.getInt("user_id");
				qUser = new User(username, userId);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return qUser;
	}

	//TODO TEST
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		ResultSet queryResult = null;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from bank_user";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			queryResult = ps.executeQuery();
			while(queryResult.next()) {
				String username = queryResult.getString("username");
				int userId = queryResult.getInt("user_id");
				User currUser = new User(username, userId);
				users.add(currUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

			users = null;
		}
		return users;
	}
	
	public boolean deleteUser(int userId) {
		boolean executed = true;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "delete from bank_user where user_id = " +userId;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			executed = false;
		}
		return executed;
	}
	
	public boolean checkForUserByUsername(String username) {
		boolean usernameExists = false;
		ResultSet results = null;
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from bank_user where username = \'" +username+ "\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			results = ps.executeQuery();
			
			if(results.next()) {
				usernameExists = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			usernameExists = false;
		}
		
		return usernameExists;
	}

	@Override
	//TODO
	public boolean updateUsername(String username, int userId) {
		boolean usernameUpdated = false;
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "update bank_user set username = \'" +username+ "\' where user_id = \'" +userId+ "\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if(ps.executeUpdate()==1) {
				usernameUpdated = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			usernameUpdated = false;
		}
		
		return usernameUpdated;
	}

	@Override
	//TODO
	public boolean updatePassword(String password, int userId) {
		boolean passwordUpdated = false;
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "update bank_user set user_password = \'" +password+ "\' where user_id = \'" +userId+ "\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if(ps.executeUpdate()==1) {
				passwordUpdated = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			passwordUpdated = false;
		}
		
		return passwordUpdated;
	}
	
}
