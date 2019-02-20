package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {

	private static final Logger logger = LogManager.getLogger(ConnectionUtil.class);
	private static final Properties props = getConnectionProperties();
	
	private ConnectionUtil() {}
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		} catch (SQLException e) {
			logger.fatal("Failed to obtain JDBC Connection: {}", e.getMessage());
			logger.fatal("SQL State: {}", e.getSQLState());
			logger.fatal("Error Code: {}", e.getErrorCode());
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			logger.fatal("Failed to load JDBC Driver: {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private static Properties getConnectionProperties() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			logger.fatal("Failed to load application.properties: {}", e.getMessage());
			throw new RuntimeException(e);
		}
		return properties;
	}
}
