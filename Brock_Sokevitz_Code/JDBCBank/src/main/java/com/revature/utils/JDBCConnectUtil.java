package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImplementation;

public class JDBCConnectUtil {

	//jdbc - java database connectivity
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	static String url="jdbc:oracle:thin:@brockrds.cbhmyymyseyv.us-east-2.rds.amazonaws.com:1521:Brockrds";
	private static String username = "";
	private static String password = "";
	
	public static String getUser() {
		return username;
	}

	public static void setUser(String user) {
		JDBCConnectUtil.username = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		JDBCConnectUtil.password = password;
	}

	//runs before the rest of the program
	static {
		try {
			log.info("JDBC driver is loaded/registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void setConnection(String url, String username, String password){		
		//bad habit to hard code credentials, better to have a file that isn't accessible	
		JDBCConnectUtil.url = url;
		JDBCConnectUtil.username = username;
		JDBCConnectUtil.password = password;
	}		
	
	public static void setConnection(String username, String password){		
		//bad habit to hard code credentials, better to have a file that isn't accessible	
		JDBCConnectUtil.username = username;
		JDBCConnectUtil.password = password;
	}	
	
	public static Connection getConnection() throws SQLException {
		
		//bad habit to hard code credentials, better to have a file that isn't accessible	
		return DriverManager.getConnection(url, username, password);
	}	
}