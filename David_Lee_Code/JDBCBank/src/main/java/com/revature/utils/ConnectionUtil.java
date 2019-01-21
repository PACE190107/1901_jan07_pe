package com.revature.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	final static Logger log = Logger.getLogger(ConnectionUtil.class);
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException, FileNotFoundException {
		String url = "";
		String username = "";
		String password = "";
		try {
		final BufferedReader bf = new BufferedReader(new FileReader("src\\main\\resources\\connect.properties"));
		url = bf.readLine();
		username = bf.readLine();
		password = bf.readLine();
		bf.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file! Exiting...");
			System.exit(0);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something is wrong with the file! Exiting...");
			System.exit(0);
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url,username,password);
	}
	
}
