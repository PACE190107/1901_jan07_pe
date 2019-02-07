package com.revature.utils;

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
		String url="jdbc:oracle:thin:@kennethdanielsdb.cxpt4bk9ingo.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username = "kennethdaniels";
		String password = "THEhundreds13";
		return DriverManager.getConnection(url,username,password);
	}
}
