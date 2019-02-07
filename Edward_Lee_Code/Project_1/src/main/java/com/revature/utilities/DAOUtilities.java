package com.revature.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Class used to retrieve DAO Implementations. Serves as a factory. Also manages a single instance of the database connection.
 */
public class DAOUtilities {
	// need to move these to a file
	private static String CONNECTION_USERNAME = null; 
	private static String CONNECTION_PASSWORD = null;
	private static String URL = null;
	
	
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException {
		try {
			getConnectionInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (connection == null) {
			DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	private static void getConnectionInfo() throws IOException {
		BufferedReader in = null;
	    try {
	        in  = new BufferedReader(new FileReader(Thread.currentThread().getContextClassLoader().getResource("connections.properties").getPath()));
	    	CONNECTION_USERNAME = in.readLine();
	        CONNECTION_PASSWORD = in.readLine();
	        URL = in.readLine();
	    }
	    finally {
	        if (in != null) {
	            in.close();
	        }
	    }
	}
}

