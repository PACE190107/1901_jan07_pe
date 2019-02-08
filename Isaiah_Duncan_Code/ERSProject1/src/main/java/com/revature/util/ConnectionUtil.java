package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	 
	final static Logger Log = Logger.getLogger(ConnectionUtil.class);
	//JDBC - java database connectivity
		static {
			try {
				Log.info("JDBC driver is loaded / registered");
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() throws SQLException {
			
			//hard coding credentials are not a good practice
			String url="jdbc:oracle:thin:@project1imd.cdrmoezcbvm7.us-east-2.rds.amazonaws.com:1521:P1DB";	
			String username = "admin";
			String password = "iamking23!";
			return DriverManager.getConnection(url,username,password);
		}
	
}
