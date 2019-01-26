package com.revature.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionUtil {
	
	static{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//JDBC - Java Database Connectivity
	
	public static Connection getConnection() throws SQLException {
		
		//use credentials - why?
		//you have access to my database as long as you are in this building
		
		//Hard coding credentials are not a good habit
		String url="jdbc:oracle:thin:@garcia-1901rds.csnuq8ktjehc.us-east-2.rds.amazonaws.com:1521:RDS1901";	
		String user = "Programmer";
		String pass = "p4ssw0rd";
		return DriverManager.getConnection(url,user,pass);
	}
}