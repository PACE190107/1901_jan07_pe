package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {

	// jdbc - java database connectivity
	final static Logger log = Logger.getLogger(ConnectionUtil.class);

	private static ConnectionUtil cu = null;
	private static Properties prop = new Properties();

	private ConnectionUtil() {
		super();
		InputStream dbProps = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			prop.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionUtil getInstance() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			// We have to register the driver class
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}