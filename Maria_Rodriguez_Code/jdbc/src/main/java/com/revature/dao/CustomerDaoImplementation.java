package com.revature.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


import com.revature.models.Customer;
import com.revature.services.CustomerService;
import com.revature.utils.JDBCConnectionUtil;

import oracle.jdbc.internal.OracleTypes;

//DAO class implements DAO interface
//DAO class inserts/retrieves data from database
//In our example connection to database will be handled by utils

public class CustomerDaoImplementation implements CustomerDao {

	private static CustomerDaoImplementation custDao;
	
	final static Logger log = Logger.getLogger(CustomerDaoImplementation.class);
	
	private CustomerDaoImplementation() {
	}
	
	public static CustomerDaoImplementation getCustDao() {
		if (custDao == null) {
			custDao = new CustomerDaoImplementation();
		}
		return custDao;
	}
	
	
	
	@Override
	public boolean insertCustomer(Customer cust) {
		
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into Customer values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cust.getId());
			ps.setString(2, cust.getFirstName());
			ps.setString(3, cust.getLastName());
			ps.setString(4, cust.getUserName());
			ps.setString(5, cust.getPassword());
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	//works!////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean insertCustomerProcedure(Customer cust) {
		Connection conn = null;
		CallableStatement stmt = null;
		
		
		try  {conn = JDBCConnectionUtil.getConnection(); 
			//CallableStatement stmt = con.prepareCall(sql);
			stmt = conn.prepareCall("{CALL REGISTER_NEW_USER(?,?,?,?,?)}");
			
			stmt.setString(1, cust.getFirstName());
			stmt.setString(2, cust.getLastName());
			stmt.setString(3, cust.getUserName());
			stmt.setString(4, cust.getPassword());
			stmt.setString(5, cust.getSuperuser());
			
		
			if(stmt.executeUpdate() >= 0) 
			{
				return true;
			} 
			
		else {
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			
			log.error("error occured in catch block of insert customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;	
		
		
		
	
	}finally {
		
			try {
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
	}
	}
	
	
	
	//works!///////////////////////////////////////////////////////////////////////////

	@Override
	public Customer getCustomer( String userName, String passWord ) {
				
		try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			
			//Does user credential Validation...
			String sqlUsername = "SELECT * from USERS where USERNAME = '" + userName + "' AND PASSWORD = '" + passWord + "'";
			
			Statement stmt = conn.createStatement();
			ResultSet uNameResults = stmt.executeQuery(sqlUsername);
										
			while( uNameResults.next() ) {
				log.info("inside the while loop for getCustomer");
				return new Customer(
						uNameResults.getString("USER_ID"), 
						uNameResults.getString("FIRST_NAME"), 
						uNameResults.getString("LAST_NAME"),
						uNameResults.getString("USERNAME"),
						uNameResults.getString("PASSWORD")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	//View Balance
	@Override
	public double getBalance( String user_id ) {
				
		try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			String sql= ("{CALL CHECK_BALANCE(?)}");
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, user_id);
			
			ResultSet results = null;
			
				while( results.next() ) {
				log.info("inside the while loop for getCustomer");
				System.out.println(results.getDouble("ACCOUNT_BALANCE"));
				
				return (results.getDouble("ACCOUNT_BALANCE")
//						Results.getString("FIRST_NAME"), 
//						Results.getString("LAST_NAME"),
//						Results.getString("USERNAME"),
//						Results.getString("PASSWORD")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		return -1;
		
		
	}
		
		
		
		
		
		
		
return 0;
}
	
	
	
	
	public double balanceCheck(String new_id) {
		//System.out.println("In balance check");
		double balance = 0;
		//System.out.println(new_id);
		try(Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "SELECT ACCOUNT_BALANCE from accounts where USER_ID = '" + new_id+ "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				balance = results.getDouble("ACCOUNT_BALANCE");
			}
			//System.out.println("Balance: " + balance);
		} catch (SQLException e) {
			
		}
		
		return balance;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Customer> getAllCustomers() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from USERS";
			
			log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			log.info("viewing the results");
			List<Customer> allCustomers = new ArrayList<>();
			while (results.next()) {
				allCustomers.add(new Customer(
						results.getString("USER_ID"), 
						results.getString("FIRST_NAME"), 
						results.getString("LAST_NAME"),
						results.getString("USERNAME"),
						results.getString("PASSWORD"))
						);
			}
			return allCustomers;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}
//////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean getsSuperUser( String userName, String passWord ) {
				
		try ( Connection conn = JDBCConnectionUtil.getConnection() ){
			
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			
			//Does user credential Validation...
			String sqlUsername = "SELECT * from USERS where SUPERUSER = y";
			
			Statement stmt = conn.createStatement();
			ResultSet uNameResults = stmt.executeQuery(sqlUsername);
										
			if( uNameResults.next() ) {
				//log.info("inside the while loop for getCustomer");
			return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public boolean updateCustomer(String id, String firstName, String lastName) {
//			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
//				String sql= ("{CALL UPDATE_USER(?,?,?)}");
//				CallableStatement stmt = conn.prepareCall(sql);
//				stmt.setString(1, id);
//				stmt.setString(2, firstName);
//				stmt.setString(3, lastName);
//			
//				ResultSet rs = stmt.executeQuery();
//			while( rs.next()) {
//				
//						
//			}
				
			//}
			return false;
			
			//use a scanner to get their first name and last name customer.getId() 
		}
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public boolean withdraw(String user_id, double withdrawAmount) {
			//System.out.println(user_id);
			//System.out.println(withdrawAmount);
			System.out.println("Account details for User Id: " + user_id);
			double balance = returnBalance(user_id);
			System.out.println("Current Balance: " + balance);
			
			
			if(withdrawAmount <= balance) {
				//do withdraw
				try ( Connection conn = JDBCConnectionUtil.getConnection() ){
					String sql= ("{CALL WITHDRAW(?,?)}");
					CallableStatement stmt = conn.prepareCall(sql);
					stmt.setString(1, user_id);
					stmt.setDouble(2, withdrawAmount);
					
					//ResultSet rs = stmt.executeQuery();
					if(stmt.executeUpdate() >= 0) {
						return true;
					} else {
						throw new SQLException();
					}
					
				} catch(SQLException e) {
					return false;
				}
			} else {
				//throw over draft exception
				
				System.out.println("Overdraft!!!!!");
				return false;
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		static double returnBalance(String user_id) {
			
			double balance = 0;
			//System.out.println(user_id);
			try(Connection conn = JDBCConnectionUtil.getConnection()) {
				String sql = "SELECT ACCOUNT_BALANCE from accounts where USER_ID = '" + user_id+ "'";
				Statement stmt = conn.createStatement();
				ResultSet results = stmt.executeQuery(sql);
				while (results.next()) {
					balance = results.getDouble("ACCOUNT_BALANCE");
				}
				//System.out.println("Balance: " + balance);
			} catch (SQLException e) {
				
			}
			
			
			return balance;
			
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public boolean deposit(String user_id, double depositAmount) {
			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
				String sql= ("{CALL DEPOSIT(?,?)}");
				CallableStatement stmt = conn.prepareCall(sql);
				stmt.setString(1, user_id);
				stmt.setDouble(2, depositAmount);
				
				//ResultSet rs = stmt.executeQuery();
				
				if(stmt.executeUpdate() >= 0 ) {
					return true;
				} else {
					throw new SQLException();
				}
				 
			} catch(SQLException e) {
				return false;
			}
			
			
		}

		
		public boolean deleteAccount(String user_id) {
			
			//System.out.println(user_id);
			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
				String sql= ("{CALL DELETE_ACCOUNT(?)}");
				CallableStatement stmt = conn.prepareCall(sql);
				stmt.setString(1, user_id);
				
				
				
				if(stmt.executeUpdate() >= 0 ) {
					return true;
				} else {
					throw new SQLException();
				}
				//ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			return false;
		}
			
			
			
			}
		
		
		
		
		public boolean updateUser(String user_id, String firstName, String lastName) {
			//System.out.println(user_id);
			try ( Connection conn = JDBCConnectionUtil.getConnection() ){
				String sql= ("{CALL UPDATE_USER(?,?,?)}");
				CallableStatement stmt = conn.prepareCall(sql);
				stmt.setString(1, user_id);
				stmt.setString(2,firstName);
				stmt.setString(3,lastName);
				
				
				if(stmt.executeUpdate() >= 0 ) {
					return true;
				} else {
					throw new SQLException();
				}
				//ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			return false;
		}
			
		
		}
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			public boolean createBankAccount(int accountBalance, double accountNumber, String firstName, String lastName) {
				System.out.println("new Balance amount" + accountBalance);
				System.out.println("account number" + accountNumber);
				System.out.println("firstname " + firstName);
				System.out.println("lastname " + lastName);
				try ( Connection conn = JDBCConnectionUtil.getConnection() ){
					String sql= ("{CALL CREATE_ACCOUNT(?,?,?,?)}");
					CallableStatement stmt = conn.prepareCall(sql);
					stmt.setDouble(1, accountBalance);
					stmt.setDouble(2, accountNumber);
					stmt.setString(3,  firstName);
					stmt.setString(4, lastName);
					
					
					if(stmt.executeUpdate() >= 0 ) {
						return true;
					} else {
						throw new SQLException();
					}
					//ResultSet rs = stmt.executeQuery();
			} catch (SQLException e) {
				return false;
			}
			
			}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				public boolean deleteUser(String user) {
					try ( Connection conn = JDBCConnectionUtil.getConnection() ){
						String sql= ("{CALL DELETE_USER(?)}");
						CallableStatement stmt = conn.prepareCall(sql);
						stmt.setString(1, user);
						
						
						
						if(stmt.executeUpdate() >= 0 ) {
							return true;
						} else {
							throw new SQLException();
						}
						//ResultSet rs = stmt.executeQuery();
				} catch (SQLException e) {
					return false;
				}
					
					
					
					
				
				
			
}
			
}
