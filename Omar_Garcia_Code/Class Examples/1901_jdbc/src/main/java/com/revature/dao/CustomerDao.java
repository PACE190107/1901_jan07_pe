package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {

	public boolean insertCustomer(Customer cust) throws SQLException;
	public boolean insertCustomerProcedure(Customer cust);
	public Customer getCustomer() throws SQLException;
	public List<Customer> getAllCustomer() throws SQLException;
	
}
