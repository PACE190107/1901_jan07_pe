package com.revature.ers.database;

import java.sql.DriverManager;
import java.sql.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeModel
	{

		private String fname = null;
		private String lname = null;
		private int id = 0;
		private boolean isManager = false;
		private static final String url = "jdbc:oracle:thin:@ers.cg60uka19uhu.us-east-1.rds.amazonaws.com:1521:ERS";
		private static final String un = "asaha";
		private static final String pw = "anupsaha";

		public boolean login(String username, String password) 
			{

				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						PreparedStatement ps = conn.prepareStatement("Select * from employees where username = ? and password =?");
						ps.setString(1, username);
						ps.setString(2, password);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							{
								this.id = rs.getInt(1);
								this.fname = rs.getString(2);
								this.lname = rs.getString(3);
								if(rs.getInt(6) > 0)
									{
										this.isManager = true;
									}
								else
									{
										this.isManager = false;
									}
								return true;
							}
						else
							{
								return false;
							}
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}
				return false;
			}
		public String getName()
		{
			return fname + " " + lname;
		}
		public int checkManager()
		{
			if(isManager == false)
				{
					return 0;
				}
			else
				{
					return 1;
				}
		}

		public int getID()
		{
			return id;
		}
	}
