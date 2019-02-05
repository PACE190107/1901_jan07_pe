package com.revature.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionUtil 
{
	final static Logger log = Logger.getLogger(ConnectionUtil.class);
	private static BufferedReader br = null;
	private static String url = "";
	private static String userN = "";
	private static String passW = "";
	
	static
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("JDBC driver is loaded/registered");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{	
		try
		{
			br = new BufferedReader(new FileReader("C:/my_git_repos/1901_jan07_pe/Marcellus_Traynor_Code/ERS/src/main/resources/database.properties"));
			try 
			{
				br.readLine();
				url = br.readLine();
				userN = br.readLine();
				passW = br.readLine();
				return DriverManager.getConnection(url, userN, passW);
			} catch (IOException e) 
			{
				log.error("error occured in IO catch block while reading in standardConnection ConnectionUtil method");
				log.error(e.getMessage());
				log.error(e.getStackTrace());
			} 
		} catch (FileNotFoundException e) 
		{
			log.error("error occured in FNF catch block in standardConnection ConnectionUtil method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return null;
	}
}