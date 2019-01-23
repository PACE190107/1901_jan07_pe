package com.revature.dao;

import java.util.Scanner;
import java

public static CustomerDaoImplementation getCustDao() implements CustomerDao {

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
			ps.setString(1, cust.getFirstName());
			ps.setString(2, cust.getLastName());
			ps.setString(3, cust.getUserName());
			ps.setString(4, cust.getPassword());
			ps.registerOutParameter(5, Types.NUMERIC);
			ps.executeUpdate();
			if(ps.getInt(5) > 0) {
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
		String userName = "";
		String password = "";
		Scanner scanner = new Scanner(System.in);
		try (Connection conn = JDBCConnectionUtil.getConnection()){
			System.out.println("Enter your username: ");
			userName = scanner.nextLine();
			//bad practice - never hard code values in a method
			//how to avoid - using prepared statement - PS supports parameterized sql
			String sql = "select * from BANK_CUST where CUST_FIRSTNAME = 'Peyton'";
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				log.info("inside the while loop for getCustomer");
				return new Customer(
						results.getInt("CUST_ID"), 
						results.getString("CUST_FIRSTNAME"), 
						results.getString("CUST_LASTNAME"),
						results.getString("CUST_USERNAME"),
						results.getString("CUST_PASSWORD")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public Customer getCustomer() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			log.info("creating statement object");
			Statement stmt = conn.createStatement();
			String sql = "select * from employee_1901";
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
		}
	}
}