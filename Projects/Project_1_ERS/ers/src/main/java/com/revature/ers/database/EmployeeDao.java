package com.revature.ers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao
	{
		private static final String url = "jdbc:oracle:thin:@ers.cg60uka19uhu.us-east-1.rds.amazonaws.com:1521:ERS";
		private static final String un = "asaha";
		private static final String pw = "anupsaha";
		public static List<Employee> getAll()
		{
			List<Employee> emp = new ArrayList<Employee>();
			
			try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(url, un, pw);
					Statement s = conn.createStatement();
					
					ResultSet rs = s.executeQuery("Select * from employees");
					
					while(rs.next())
						{
							System.out.println(rs.getInt(1));
							
							emp.add(new Employee(
									
									rs.getInt(1),
									rs.getString(2),
									rs.getString(3)
									));
							
						}
					
					
					
				} catch (SQLException e)
				{

				} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}

			return emp;
		}
	}
