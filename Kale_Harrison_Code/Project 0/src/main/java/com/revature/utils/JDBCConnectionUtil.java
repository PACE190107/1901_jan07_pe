package com.revature.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JDBCConnectionUtil {
	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	static {
		try {
			log.info("JDBC driver is loaded / registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\my_git_repos\\1901_jan07_pe\\Kale_Harrison_Code\\Project 0\\src\\main\\resources\\Properties.txt"));
			String url = in.readLine();
			String username = in.readLine();
			String password = in.readLine();
			in.close();
			return DriverManager.getConnection(url,username,password);
		}catch(IOException e) {
			System.out.println("Connection failed");
		}
		return null;
	}
	
	public static Connection getConnection(String username, String password) throws SQLException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("C:\\my_git_repos\\1901_jan07_pe\\Kale_Harrison_Code\\Project 0\\src\\main\\resources\\Properties.txt"));
			String url = in.readLine();
			in.close();
			return DriverManager.getConnection(url,username,password);
		}catch(IOException e) {
			System.out.println("Connection failed");
		}
		return null;
		

		
		
	}

}
