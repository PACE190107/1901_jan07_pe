package com.revature.projectzero.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class JDBCConnectionUtil {

	final static Logger log = Logger.getLogger(JDBCConnectionUtil.class);
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		String url="jdbc:oracle:thin:@rds1901.cugpj0vbdsnx.us-east-1.rds.amazonaws.com:1521:ORCL";	
		String user = "engineer";
		String pass = "p4ssword";
		return DriverManager.getConnection(url, user, pass);
	}
}

