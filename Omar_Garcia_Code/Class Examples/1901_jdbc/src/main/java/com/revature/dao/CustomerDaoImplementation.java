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
import com.revature.util.JDBCConnectionUtil;

//Dao class implements DAO interface
//DAO class retrieves/Inserts data from database
//In our example connection to database will be handled by utils

public class CustomerDaoImplementation implements CustomerDao {

	private static CustomerDaoImplementation custimp;
	final static Logger log = Logger.getLogger(CustomerDaoImplementation.class);

	private CustomerDaoImplementation() {

	}

	public static CustomerDaoImplementation getCustomerImplementation() {
		if (custimp == null) {
			custimp = new CustomerDaoImplementation();
		}
		return custimp;
	}

	@Override
	public boolean insertCustomer(Customer cust) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "INSERT into customer values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cust.getId());
			ps.setString(2, cust.getFirstname());
			ps.setString(3, cust.getLastname());
			ps.setString(4, cust.getUsername());
			ps.setString(5, cust.getPassword());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
		}
		return false;
	}

	@Override
	public boolean insertCustomerProcedure(Customer cust) {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "call into_customer(?,?,?,?)";
			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, cust.getFirstname());
			ps.setString(2, cust.getLastname());
			ps.setString(3, cust.getUsername());
			ps.setString(4, cust.getPassword());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			log.error("error occured in catch block of insert customer dao implementation method");
		}
		return false;
	}

	@Override
	public Customer getCustomer() throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from customer where C_FIRSTNAME = 'Peyton'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while (results.next()) {
				log.info("Inside ");
				return new Customer(results.getInt("C_ID"), results.getString("C_FIRSTNAME"),
						results.getString("C_LASTNAME"), results.getString("C_USERNAME"),
						results.getString("C_PASSWORD"));
			}
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() throws SQLException {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			Statement stmt = conn.createStatement();
			log.info("creating statement");
			String sql = "select * from customer";
			log.info("Executing the query");
			ResultSet results = stmt.executeQuery(sql);
			log.info("Viewing Results");
			List<Customer> allcustomers = new ArrayList<>();
			while (results.next()) {
				allcustomers.add(new Customer(results.getInt("C_ID"), results.getString("C_FIRSTNAME"),
						results.getString("C_LASTNAME"), results.getString("C_USERNAME"),
						results.getString("C_PASSWORD")));
			}
			return allcustomers;
		}
	}

}
