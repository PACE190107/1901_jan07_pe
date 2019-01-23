package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.Exceptions.*;
import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class UserDaoImplementation implements UserDao {

	private static UserDaoImplementation userdi;
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);

	private UserDaoImplementation() {

	}

	public static UserDaoImplementation getUserDao() {
		if (userdi == null) {
			userdi = new UserDaoImplementation();
		}
		return userdi;
	}

	public User login(String username, String password) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USERS where U_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return new User(result.getInt("U_ID"), result.getString("U_FIRST"), result.getString("U_LAST"),
						result.getString("U_USERNAME"), result.getString("U_PASSWORD"), result.getInt("SUPERUSER"));
			}
		}
		return new User();
	}

	public User register(String first, String last, String username, String password) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "CALL INSERT_USER(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, first);
			cs.setString(2, last);
			cs.setString(3, username);
			cs.setString(4, password);
			cs.setInt(5, 0);
			cs.execute();

			sql = "SELECT * FROM USERS where U_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return new User(result.getInt("U_ID"), result.getString("U_FIRST"), result.getString("U_LAST"),
						result.getString("U_USERNAME"), result.getString("U_PASSWORD"), result.getInt("SUPERUSER"));
			}
		}
		return new User();
	}

	public List<User> getAllUsers() throws SQLException, EmptyUsersException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from USERS";
			Statement stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(sql);
			List<User> users = new ArrayList<User>();
			while (results.next()) {
				users.add(new User(results.getInt(1), results.getString(2), results.getString(3), results.getString(4),
						results.getString(5), results.getInt(6)));
			}
			if (users.isEmpty()) {
				throw new EmptyUsersException();
			}
			return users;
		}
	}

	public void deleteAllUsers() throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from USERS WHERE SUPERUSER = 0";
			Statement stmnt = conn.createStatement();
			ResultSet results = stmnt.executeQuery(sql);
			while (results.next()) {
				sql = "DELETE FROM ACCOUNT WHERE A_UID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, results.getInt(1));
				ps.execute();
			}

			sql = "DELETE FROM USERS WHERE SUPERUSER = 0";
			stmnt = conn.createStatement();
			stmnt.execute(sql);
		}
	}

	public boolean insertUser(User user) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "CALL INSERT_USER(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUserName());
			cs.setString(4, user.getPassWord());
			cs.setInt(5, user.getSuperuser());
			cs.execute();

			sql = "SELECT * FROM USERS where U_PASSWORD LIKE GET_USER_HASH(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				new User(result.getInt("U_ID"), result.getString("U_FIRST"), result.getString("U_LAST"),
						result.getString("U_USERNAME"), result.getString("U_PASSWORD"), result.getInt("SUPERUSER"));
			}
		}
		if (user.getId() > 0) {
			return true;
		}
		return false;
	}

	public User getUser(int id) throws SQLException, UserNotFoundException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE U_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			User user = new User();
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				user.setId(result.getInt(1));
				user.setFirstName(result.getString(2));
				user.setLastName(result.getString(3));
				user.setUserName(result.getString(4));
				user.setPassWord(result.getString(5));
				user.setSuperuser(result.getInt(6));
			} else {
				throw new UserNotFoundException();
			}
			return user;
		}
	}

	public void updateUser(User user, boolean newPassword) throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			if (newPassword) {
				String sql = "Update USERS SET U_FIRST = ?, U_LAST = ?, U_USERNAME = ?, U_PASSWORD = GET_USER_HASH(?,?), SUPERUSER = ? WHERE U_ID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getUserName());
				ps.setString(4, user.getUserName());
				ps.setString(5, user.getPassWord());
				ps.setInt(6, user.getSuperuser());
				ps.setInt(7, user.getId());
				ps.executeUpdate();
				
			} else {
				String sql = "Update USERS SET U_FIRST = ?, U_LAST = ?, U_USERNAME = ?, U_PASSWORD = ?, SUPERUSER = ? WHERE U_ID = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getUserName());
				ps.setString(4, user.getPassWord());
				ps.setInt(5, user.getSuperuser());
				ps.setInt(6, user.getId());
				ps.executeUpdate();
			}
		}
	}
}
