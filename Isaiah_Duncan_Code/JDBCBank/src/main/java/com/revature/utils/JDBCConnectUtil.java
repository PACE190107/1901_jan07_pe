package com.revature.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

//this class establishes a connection to the database
public class JDBCConnectUtil {

	final static Logger Log = Logger.getLogger(JDBCConnectUtil.class);
	//JDBC - java database connectivity
	static {
		try {
			//Log.info("JDBC driver is loaded / registered");
			//load the driver class
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static BufferedReader bf = null;
	static String url = null;
	static String username = null;
	static String password = null;
	
	static void readWriteBytes() throws  IOException, FileNotFoundException{
		bf = new BufferedReader(new FileReader("src\\main\\resources\\jdbc.properties"));
		url = bf.readLine();
		username = bf.readLine();
		password = bf.readLine();
			
	}
	
	
	//this method gets the connection for the database using the DriverManager and calling the getConnection function on it
	public static Connection getConnection() throws SQLException {

		try {
			readWriteBytes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//return an object of the created connection
		return DriverManager.getConnection(url,username,password);
	}
}