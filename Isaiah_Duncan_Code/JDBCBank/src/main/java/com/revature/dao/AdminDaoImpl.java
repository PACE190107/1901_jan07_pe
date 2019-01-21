package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Admin;
import com.revature.models.User;
import com.revature.utils.JDBCConnectUtil;

public class AdminDaoImpl implements AdminDao {

	private static AdminDaoImpl adminDao;
	final static Logger log = Logger.getLogger(AdminDaoImpl.class);
	private AdminDaoImpl() {
	}
	
	public static AdminDaoImpl getAdminDao() {
		if (adminDao == null) {
			adminDao = new AdminDaoImpl();
		}
		return adminDao;
	}

	@Override
	public boolean adminCheckImpl(Admin admin) {
		
		String adminUsername = admin.getAdminUsername();
		String adminPassword = admin.getAdminPassword();
		String comparedUsername = null;
		String comparedPassword = null;
		
		try (Connection conn = JDBCConnectUtil.getConnection()){
			String sql = "SELECT admin_username, admin_password FROM admins WHERE admin_username = '"+adminUsername+"'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				comparedUsername = results.getString("admin_username");
				comparedPassword = results.getString("admin_password");
			}
			if(adminUsername.equals(comparedUsername) && adminPassword.equals(comparedPassword)) {
				System.out.println("The username and passowrd combo is a match!\n");
				return true;
			}else {
				System.out.println("The username and/or passowrd you entered was not correct.\n");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
		
	}

	@Override
	public boolean createAUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
