package com.proj0.dao;

import java.sql.SQLException;
import java.util.List;

import com.proj0.models.Customer;

public interface CustomerDao {

		public boolean insertCustomer(Customer cust);
		public boolean insertCustomerProcedure(Customer cust);
		public Customer getCustomer(String username);
		public List<Customer> getAllCustomers() throws SQLException;
		
		
}
