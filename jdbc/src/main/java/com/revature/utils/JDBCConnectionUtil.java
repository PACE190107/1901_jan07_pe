package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionUtil {

	//JDBC - java database connectivity
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		
		//use your credentials -why?
		//you have access to my database as long as you are in this building
		
		//hard coding credentials are not good practice 
		String url="jdbc:oracle:thin:@rds1901.chueiwozbnfz.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "engineer"; 
		String password = "p4ssw0rd";
		return DriverManager.getConnection(url,username,password);
	}
}
