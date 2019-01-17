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
import com.revature.utils.JDBCConnectionUtil;

//Dao class implements DAO interface
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
			ps.setInt(1, cust.getId());
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

	@Override
	public boolean insertCustomerProcedure(Customer cust) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call insert_customer(?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
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

	@Override
	public Customer getCustomer() {
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			String sql = "select * from customer where C_FIRSTNAME = 'Peyton'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				log.info("inside the while loop for getCustomer");
				return new Customer(
						results.getInt("C_ID"), 
						results.getString("C_FIRSTNAME"), 
						results.getString("C_LASTNAME"),
						results.getString("C_USERNAME"),
						results.getString("C_PASSWORD")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from customer";
			
			log.info("executing the query");
			ResultSet results = stmt.executeQuery(sql);
			log.info("viewing the results");
			List<Customer> allCustomers = new ArrayList<>();
			while (results.next()) {
				allCustomers.add(new Customer(
						results.getInt("C_ID"), 
						results.getString("C_FIRSTNAME"), 
						results.getString("C_LASTNAME"),
						results.getString("C_USERNAME"),
						results.getString("C_PASSWORD"))
						);
			}
			return allCustomers;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

}
