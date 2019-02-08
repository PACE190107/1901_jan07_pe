package com.revature.util;

//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;



public class JDBSCConnectionUtil {
	static String url;	
	static String connectionUser;
	static String connectionPass;
	static String superUsername;
	static String superPass;
	
	
	final static Logger log = Logger.getLogger(JDBSCConnectionUtil.class);
	//JDBC = Java Database Connectivity
	static {
		try {
			log.info("JDBC driver is loaded / registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static Connection getConnection() throws SQLException {
		
		Properties prop = new Properties();
		InputStream input = null;

//		try {
//
//			input = new FileInputStream("conf.properties");

		try {
	        
    		String filename = "conf.properties";
    		input = JDBSCConnectionUtil.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		}

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			url = prop.getProperty("url");
			connectionUser = prop.getProperty("connectionUser");
			connectionPass = prop.getProperty("connectionPass");
			superUsername = prop.getProperty("superUsername");
			superPass = prop.getProperty("superPass");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return DriverManager.getConnection(url, connectionUser, connectionPass);
		
//		url = "jdbc:oracle:thin:@lorenzo-santibanez.clo5dbt0ejfd.us-east-2.rds.amazonaws.com:1521:RDS1901";
//		connectionUser = "lsantibanez7";
//		connectionPass = "password";
//		return DriverManager.getConnection(url, connectionUser, connectionPass);
	}
}
