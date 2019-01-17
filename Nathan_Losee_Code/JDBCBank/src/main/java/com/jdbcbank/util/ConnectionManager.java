package com.jdbcbank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection jdbcCon;

	public static void setJDBCConnection(String username, String password) {
		try {
			if (jdbcCon != null && !jdbcCon.isClosed())
				jdbcCon.close();
			jdbcCon = DriverManager.getConnection(
				"jdbc:oracle:thin:@revature-jbdcbank.cyitp3sizf7l.us-east-2.rds.amazonaws.com:1521:JBDCBANK",
				username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getJDBCConnection() {
		return jdbcCon;
	}
	
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
