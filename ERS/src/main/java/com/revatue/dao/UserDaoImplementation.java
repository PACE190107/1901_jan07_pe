package com.revatue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
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

	/// works !!
	// Method for Employee
	/// Login//////////////////////////////////////////////////////////////////////////////////////
	public User goodLogin(String email, String password) {

		System.out.println(email);
		System.out.println(password);

		try (Connection conn = JDBCConnectionUtil.getConnection()) {

			String sqlUsername = "SELECT * FROM EMPLOYEES where EMAIL = ? AND password = ?";
			PreparedStatement ps = conn.prepareStatement(sqlUsername);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet resultSet = ps.executeQuery();
			System.out.println(resultSet);
			// System.out.println(resultSet.next());

			User tempUser = null;

			while (resultSet.next()) {
				System.out.println("in while");
				tempUser = new User(resultSet.getInt("E_ID"), resultSet.getString("FIRST_NAME"),
						resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"), resultSet.getString("PASSWORD"),
						resultSet.getInt("MANAGER")

				);

			}

			System.out.println("tempUser in goodLogin: " + tempUser);
			if (tempUser == null) {

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

	// not tested yet
	// managerLoginMethod///////////////////////////////////////////////////////////////////////

	/*
	 * public User managerLogin(String email, String password) {
	 * 
	 * System.out.println(email); System.out.println(password);
	 * 
	 * try ( Connection conn = JDBCConnectionUtil.getConnection() ){ User tempUser =
	 * null;
	 * 
	 * String sqlUsername = "SELECT * FROM USERS where EMAIL = ? AND password = ?";
	 * PreparedStatement ps = conn.prepareStatement(sqlUsername); ps.setString(1,
	 * email); ps.setString(2, password);
	 * 
	 * ResultSet resultSet = ps.executeQuery(); System.out.println(resultSet);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * while(resultSet.next()) { System.out.println("in while"); tempUser = new
	 * User( resultSet.getInt("E_ID"), resultSet.getString("FIRST_NAME"),
	 * resultSet.getString("LAST_NAME"), resultSet.getString("EMAIL"),
	 * resultSet.getString("PASSWORD"), resultSet.getInt("manager")
	 * 
	 * 
	 * 
	 * 
	 * );
	 * 
	 * }
	 * 
	 * System.out.println("tempUser in managerLogin: " + tempUser); if(tempUser ==
	 * null) {
	 * 
	 * return null; } else if //System.out.println("in else");
	 * (resultSet.getInt("manager")==1);
	 * 
	 * return tempUser; }
	 * 
	 * catch (SQLException e) { e.printStackTrace(); return null; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

//Manager Method to view all employees/////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<User> viewEmployees(){
	try ( Connection conn = JDBCConnectionUtil.getConnection() ){
		
		String sql= "SELECT * FROM EMPLOYEES ";
		PreparedStatement ps = conn.prepareStatement(sql);
	
		ResultSet resultSet = ps.executeQuery();
		System.out.println(resultSet);
		
		
		
		ArrayList <User> request= new ArrayList<>();
		while(resultSet.next()) {
			System.out.println("in while");
			
			request.add(new User(
					resultSet.getInt("E_ID"),
					resultSet.getString("FIRST_NAME"),
					resultSet.getString("LAST_NAME"),
					resultSet.getString("EMAIL")));
			
			
		}
	
		return request;
	


		
	}catch(

	SQLException e)
	{
		e.printStackTrace();
		
	}
	return new ArrayList<User>();

}


//updateUser///////////////////////////////////////////////////////////////////////////////

public User updateUser(int e_id, String firstName, String lastName, String email ) {

	try (Connection conn = JDBCConnectionUtil.getConnection()) {

		String sqlUsername = "UPDATE EMPLOYEES SET FIRST_NAME = '" +firstName+ "', LAST_NAME = '" +lastName+ "', EMAIL =" +email+ "WHERE E_ID =" +e_id+ ";" ;
		PreparedStatement ps = conn.prepareStatement(sqlUsername);



		User resultSet = (User) ps.executeQuery();






	} catch (SQLException e) {
		e.printStackTrace();
	
	
	

}
	if(resultset==null) {return null;}
	else {return resultSet;}
}
}

