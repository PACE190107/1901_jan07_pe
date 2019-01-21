package com.revature.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImplementation;

public class JDBCConnectionUtil 
{
	final static Logger log = Logger.getLogger(UserDaoImplementation.class);
	private static BufferedReader br = null;
	private static String url = "";
	private static String userN = "";
	private static String passW = "";
	
	static
	{
		try {
			log.info("JDBC driver is loaded/registered");
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{		
		return DriverManager.getConnection(url, userN, passW);
	}
	
	public static void setConnection(String u, String p)
	{
		userN = u;
		passW = p;
	}
	
	public static void standardConnection()
	{
		try
		{
			br = new BufferedReader(new FileReader("src/main/resources/SuperUser.properties"));
			try 
			{
				url = br.readLine();
				userN = br.readLine();
				passW = br.readLine();
			} catch (IOException e) 
			{
				log.error("error occured in catch block while reading in standardConnection JDBCConnectionUtil method");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			}
		} catch (FileNotFoundException e) 
		{
			log.error("error occured in catch block in standardConnection JDBCConnectionUtil method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
}