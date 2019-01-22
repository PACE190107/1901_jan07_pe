package project1_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Project_jdbc {

	public static void main(String[] args) {
		try {
			connectToDatabaseUsingJDBC();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private static void connectToDatabaseUsingJDBC() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.OracleDriver");
		
		String url="jdbc:oracle:thin:@lorenzo-santibanez.clo5dbt0ejfd.us-east-2.rds.amazonaws.com:1521:RDS1901";	
		String user = "lsantibanez7";
		String pass = "Xbox494320";
		
		Scanner sc = new Scanner(System.in);
		String userinput = sc.nextLine();
		
		sc.close();
		
		
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from jdbc_Proj");
		
		while(rs.next()) {
			System.out.println(
					rs.getString(1) + " " +
					rs.getString(2) + " " +
					rs.getString(3) + " " +
					rs.getString(4) + " \n" 
					);
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	
	

}
