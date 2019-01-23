package com.revature.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.models.User;

public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	private static BufferedReader br;
	private static String url = "";
	private static String username = "";
	private static String password = "";
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			defaultCredentials();
		} catch (ClassNotFoundException e) {
			logger.error("Oracle driver - failed in ConnectionUtil.");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void setCredentials(User user) {
		username = user.getUserName();
		password = user.getPassword();
	}
	
	public static void defaultCredentials() {
		try {
			br = new BufferedReader(new FileReader("src\\main\\resources\\connection.properties"));
			url = br.readLine();
			username = br.readLine();
			password = br.readLine();
		} catch (FileNotFoundException fnfe) {
			logger.error("FileReader failed to find properties file.");
		} catch (IOException ioe) {
			logger.error("BufferedReader failed to read a line from the properties file.");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				logger.error("BufferedReader failed to close.");
			}
		}	
	}
	
	public static void printCredentials() {
		System.out.println("url: " + url + "\nuser: " + username + "\npass: " + password);
	}
}
