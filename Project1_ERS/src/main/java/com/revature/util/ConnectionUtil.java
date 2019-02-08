package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	//JDBC - Java Database Connectivity
		static {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			}catch (Exception e) {
				
			}
		}
		
		public static Connection getConnection() throws SQLException{
			//HardCoding is a BAD PRACTICE
			String url = "jdbc:oracle:thin:@mycloudrds.cdqezmhyyq5y.us-east-1.rds.amazonaws.com:1521:MYREVRDS";
			String username = "tgeorgia";
			String password = "Alex97135862";
			
			return DriverManager.getConnection(url, username, password);
		}
}
