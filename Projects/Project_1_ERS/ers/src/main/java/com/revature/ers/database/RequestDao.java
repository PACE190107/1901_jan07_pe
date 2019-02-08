package com.revature.ers.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao
	{
		private static final String url = "jdbc:oracle:thin:@ers.cg60uka19uhu.us-east-1.rds.amazonaws.com:1521:ERS";
		private static final String un = "asaha";
		private static final String pw = "anupsaha";
		
		public List<Request> getAllRequests()
			{
				List<Request> reqs = new ArrayList<Request>();
				
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						Statement s = conn.createStatement();
						
						ResultSet rs = s.executeQuery("Select * from requests");
						
						while(rs.next())
							{
								System.out.println(rs.getInt(1));
								
								reqs.add(new Request(
										
										rs.getInt(1),
										rs.getInt(2),
										rs.getDouble(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getInt(8)
										));
								
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}

				return reqs;
			}
		public List<Request> getAllPendingRequests()
		{
			List<Request> reqs = new ArrayList<Request>();
			
			try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(url, un, pw);
					Statement s = conn.createStatement();
					
					ResultSet rs = s.executeQuery("Select * from requests where approved = 0");
					
					while(rs.next())
						{
							System.out.println(rs.getInt(1));
							
							reqs.add(new Request(
									
									rs.getInt(1),
									rs.getInt(2),
									rs.getDouble(3),
									rs.getString(4),
									rs.getString(5),
									rs.getInt(6),
									rs.getString(7),
									rs.getInt(8)
									));
							
						}
					
					
					
				} catch (SQLException e)
				{

				} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}

			return reqs;
		}
		
		public List<Request> getMyPendingRequests(int id)
			{
				List<Request> reqs = new ArrayList<Request>();
				
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						PreparedStatement s = conn.prepareStatement("Select * from requests where requester_id=? and approved = 0");
						
						s.setInt(1, id);
						
						ResultSet rs = s.executeQuery();
						
						while(rs.next())
							{
								System.out.println(rs.getInt(1));
								
								reqs.add(new Request(
										
										rs.getInt(1),
										rs.getInt(2),
										rs.getDouble(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getInt(8)
										));
								
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}

				return reqs;
			}
		
		public List<Request> getReqFromEmployee(int id)
		{
			List<Request> reqs = new ArrayList<Request>();
			
			try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(url, un, pw);
					PreparedStatement s = conn.prepareStatement("select * from requests where requester_id = ?");
					
					s.setInt(1, id);
					ResultSet rs = s.executeQuery();
					
					while(rs.next())
						{
							System.out.println(rs.getInt(1));
							
							reqs.add(new Request(
									
									rs.getInt(1),
									rs.getInt(2),
									rs.getDouble(3),
									rs.getString(4),
									rs.getString(5),
									rs.getInt(6),
									rs.getString(7),
									rs.getInt(8)
									));
							
						}
					
					
					
				} catch (SQLException e)
				{

				} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}

			return reqs;
		}
		
		
		public boolean makerequest(int empid, double amount, String cat, String comments)
		{
			try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(url, un, pw);
					
					CallableStatement cs = conn.prepareCall("{call makerequest(?, ?, ?, ?)}");
					
					cs.setInt(1, empid);
					cs.setDouble(2, amount);
					cs.setString(3, cat);
					cs.setString(4, comments);
					
					cs.execute();
				}
			catch(SQLException e)
				{
					
				} catch (ClassNotFoundException e)
				{
					
					e.printStackTrace();
				}
			
			return false;
		}
		
		public boolean approveRequest(int empid, int reqid)
			{
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						
						CallableStatement cs = conn.prepareCall("{call approverequest(?, ?)}");
						
						cs.setInt(1, empid);
						cs.setInt(2, reqid);
						
						cs.execute();
						return true;
					}
				catch(SQLException e)
					{
						
					} catch (ClassNotFoundException e)
					{
						
						e.printStackTrace();
					}
				
				return false;
			}
		
		public boolean denyRequest(int empid, int reqid)
			{
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						
						CallableStatement cs = conn.prepareCall("{call denyrequest(?, ?)}");
						
						cs.setInt(1, empid);
						cs.setInt(2, reqid);
						
						
						cs.execute();
						return true;
					}
				catch(SQLException e)
					{
						
					} catch (ClassNotFoundException e)
					{
						
						e.printStackTrace();
					}
				
				return false;
			}
		
		
		public List<Request> getResolvedRequests()
			{
				List<Request> reqs = new ArrayList<Request>();
				
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						Statement s = conn.createStatement();
						
						ResultSet rs = s.executeQuery("Select * from requests where approved > 0");
						
						while(rs.next())
							{
								System.out.println(rs.getInt(1));
								
								reqs.add(new Request(
										
										rs.getInt(1),
										rs.getInt(2),
										rs.getDouble(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getInt(8)
										));
								
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}

				return reqs;
			}
		
		public List<Request> getMyApproved(int id)
			{
				List<Request> reqs = new ArrayList<Request>();
				
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						PreparedStatement s = conn.prepareStatement("Select * from requests where requester_id=? and approved = 1");
						
						s.setInt(1, id);
						
						ResultSet rs = s.executeQuery();
						
						while(rs.next())
							{
								System.out.println(rs.getInt(1));
								
								reqs.add(new Request(
										
										rs.getInt(1),
										rs.getInt(2),
										rs.getDouble(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getInt(8)
										));
								
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}

				return reqs;
			}
		public List<Request> getMyDenied(int id)
			{
				List<Request> reqs = new ArrayList<Request>();
				
				try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(url, un, pw);
						PreparedStatement s = conn.prepareStatement("Select * from requests where requester_id=? and approved = 2");
						
						s.setInt(1, id);
						
						ResultSet rs = s.executeQuery();
						
						while(rs.next())
							{
								System.out.println(rs.getInt(1));
								
								reqs.add(new Request(
										
										rs.getInt(1),
										rs.getInt(2),
										rs.getDouble(3),
										rs.getString(4),
										rs.getString(5),
										rs.getInt(6),
										rs.getString(7),
										rs.getInt(8)
										));
								
							}
						
						
						
					} catch (SQLException e)
					{

					} catch (ClassNotFoundException e)
						{
							e.printStackTrace();
						}

				return reqs;
			}
	
	}
