package com.revature.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String url;
	private static String user;
	private static String pass;
	
	static {
		dbConnectionInfo();
	}
	
	public static Connection getConnection() throws SQLException {
		//hard coding credentials is not a good practice
		//how do you establish connection using jdbc? 
		Connection conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}
	
	public static void dbConnectionInfo() {
		try {
			BufferedReader readFile = new BufferedReader(new FileReader("..\\..\\..\\jdbcbank.properties"));
			String s;
			boolean dbsLoc = false;
			boolean dbsUser = false;
			boolean dbsPass = false;
			while((s=readFile.readLine()) != null ) { 
				for(String p : s.split(" ")) {
					if(dbsLoc) {
						url = p;
						dbsLoc = false;
						break;
					} else if(dbsUser) {
						user = p;
						dbsUser = false;
						break;
					} else if(dbsPass) {
						pass = p;
						dbsPass = false;
						break;
					}
					switch(p){
						case "DBSLocation":
							dbsLoc = true; break;
						case "DBSLogin":
							dbsUser = true; break;
						case "DBSPassword":
							dbsPass = true; break;
						default: break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
