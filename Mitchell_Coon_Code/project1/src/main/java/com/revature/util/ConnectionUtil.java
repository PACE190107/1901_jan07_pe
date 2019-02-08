package com.revature.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	final static Logger log = Logger.getLogger(ConnectionUtil.class);
	
	static {
		try {
			log.info("JDBC driver is loaded / registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ConnectionUtil cu = null;
	public static ConnectionUtil getInstance() {
		if(cu==null)
			cu=new ConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@rds1901.chysfel2sm66.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "macoon";
		String password = "password12345";
		try {
			// We have to register the driver class
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url,username,password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
