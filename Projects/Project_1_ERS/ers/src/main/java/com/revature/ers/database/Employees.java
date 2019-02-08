package com.revature.ers.database;


import java.sql.*;

public class Employees
	{
		private static final String url = "jdbc:oracle:thin:@ers.cg60uka19uhu.us-east-1.rds.amazonaws.com:1521:ERS";
		private static final String un = "asaha";
		private static final String pw = "anupsaha";

		public boolean register(String username, String password, String fname, String lname, int man) 
			{

				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						PreparedStatement prep = conn.prepareStatement("select * from employees where username = ?");
						prep.setString(1, username);
						
						ResultSet rs = prep.executeQuery();
						
						if(rs.next())
							{
								return false;
							}
						else
							{
								CallableStatement ps = conn.prepareCall("{call register(?,?,?,?,?)}");
								ps.setString(1, fname);
								ps.setString(2, lname);
								ps.setString(3, password);
								ps.setString(4, username);
								ps.setInt(5, man);
								
								ps.executeUpdate();
								return true;
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}
				return false;
			}
		
	}
