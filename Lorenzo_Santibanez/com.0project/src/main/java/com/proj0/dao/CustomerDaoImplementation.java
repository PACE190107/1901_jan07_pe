package com.proj0.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.proj0.models.Customer;
import com.proj0.util.JDBSCConnectionUtil;


//Dao class implements Dao interface
//Dao class insert/retreives data from database
//In our example connection to database will be handled by utils
public class CustomerDaoImplementation implements CustomerDao {

	private static CustomerDaoImplementation custDao;
	final static Logger log = Logger.getLogger(CustomerDaoImplementation.class);
	
	private CustomerDaoImplementation() {
		
	}
	
	public static CustomerDaoImplementation getCustDao() {
		if(custDao == null) {
			custDao = new CustomerDaoImplementation();
		}
		
		return custDao;
	}

	@Override
	public boolean insertCustomer(Customer cust) {
		
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			String sql = "EXEC INSERT_CUSTOMER(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, cust.getId());
			ps.setString(1, cust.getFirstName());
			ps.setString(2, cust.getLastName());
			ps.setString(3, cust.getUserName());
			ps.setString(4, cust.getPassword());
			
			if(ps.executeUpdate() > 0){
				return true;
			}else {
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			
			log.error("error occured in catch block of insert "
					+ "customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public boolean insertCustomerProcedure(Customer cust) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			String sql = "call insert_account(?,?,?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, cust.getFirstName());
			ps.setString(2, cust.getLastName());
			ps.setString(3, cust.getUserName());
			ps.setString(4 , cust.getPassword());
			ps.setInt(5, cust.getStartDeposit());
			ps.registerOutParameter(6, Types.NUMERIC);
			ps.executeUpdate();
			if(ps.getInt(6) > 0){
				return true;
				
			}else {
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			
			log.error("error occured in catch block of insert "
					+ "customer dao implementation method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
		return false;
	}

	@Override
	public Customer getCustomer(String username) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			//bad practice never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			String sql = "Select * from ACCOUNT where A_USERNAME = " + "'" + username + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//log.info("inside the while loop for getCustomer");
				return new Customer(
								results.getInt("A_ID"),
								results.getString("A_FIRSTNAME"),
								results.getString("A_LASTNAME"),
								results.getString("A_USERNAME"),
								results.getString("A_PASSWORD")
								);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getBalance(int id) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			String sql = "Select * from account_info where A_ID = " + "'" + id + "'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				//log.info("inside the while loop for getCustomer");
				return new Customer(
								results.getInt("A_ID"),
								results.getInt("A_BALANCE"),
								results.getInt("BANK_ACCOUNT_ID")
								);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteUser(int user) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			
			
			String sql = "call delete_user(?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, user);
			ps.registerOutParameter(2, Types.NUMERIC);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean modify(int user, String action, String modifyTo) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			String sql;
			CallableStatement ps;
			
			if("A_BALANCE".contentEquals(action)) {
				int modifYTo = Integer.parseInt(modifyTo);
				sql = "call modify_account_balance(?,?)";
				ps = conn.prepareCall(sql);
				ps.setInt(1, modifYTo);
				ps.setString(2, Integer.toString(user));
				ps.executeUpdate();
			}
			else if("A_FIRSTNAME".contentEquals(action)){
				sql = "call modify_account_firstname(?,?)";
				ps = conn.prepareCall(sql);
				ps.setString(1, modifyTo);
				ps.setString(2, Integer.toString(user));
				ps.executeUpdate();
			}
			else if("A_LASTNAME".contentEquals(action)){
				sql = "call modify_account_lastname(?,?)";
				ps = conn.prepareCall(sql);
				ps.setString(1, modifyTo);
				ps.setString(2, Integer.toString(user));
				ps.executeUpdate();
			}
			else if("A_USERNAME".contentEquals(action)){
				sql = "call modify_account_username(?,?)";
				ps = conn.prepareCall(sql);
				ps.setString(1, modifyTo);
				ps.setString(2, Integer.toString(user));
				ps.executeUpdate();
			}
			else if("A_PASSWORD".contentEquals(action)){
				sql = "call modify_account_password(?,?)";
				ps = conn.prepareCall(sql);
				ps.setString(1, modifyTo);
				ps.setString(2, Integer.toString(user));
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Customer> getAllCustomers() {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			//log.info("executing the query");
			Statement stmt = conn.createStatement();
			String sql = "select * from account";
			
			//log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			//log.info("viewing the results");
			List<Customer> allCustomers = new ArrayList<>();
			while(results.next()) {
				allCustomers.add(new Customer(
						results.getInt("A_ID"),
						results.getString("A_FIRSTNAME"),
						results.getString("A_LASTNAME"),
						results.getString("A_USERNAME"),
						results.getString("A_PASSWORD"))
						);
			}
			return allCustomers;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public boolean depositWithdraw(int depositWithdraw, String username, int id) {
		try(Connection conn = JDBSCConnectionUtil.getConnection()){
			String sql = "call DEPOSIT(?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setInt(1, depositWithdraw);
			ps.setInt(2, id);
			ps.registerOutParameter(3, Types.NUMERIC);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
			log.error("error occured in catch block of insert "
					+ "depositWithdraw method");
			log.error(e.getMessage());
			log.error(e.getStackTrace());
			return false;
		}
		return true;
		
	}																											

}
