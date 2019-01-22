package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 


public class JDBCConnectionUtil {

	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	//JDBC - java database connectivity
	static {
		try {
			//log.info("JDBC driver is loaded / registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src\\main\\resources\\connectivity.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String url = "";
		String username = "";
		String password = "";
		
		try {
			url = br.readLine();
			username = br.readLine(); 
			password = br.readLine();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		return DriverManager.getConnection(url,username,password);
	}
}