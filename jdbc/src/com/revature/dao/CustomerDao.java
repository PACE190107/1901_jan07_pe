package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	public boolean insertCustomer(Customer cust);
	public boolean insertCustomerProcedure(Customer cust);
	public Customer getCustomer();
	public List<Customer> getAllCustomers();
	boolean registerCustomer(Customer cust);
}
