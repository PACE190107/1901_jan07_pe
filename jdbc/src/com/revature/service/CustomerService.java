package com.revature.service;

import java.util.List;

import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;

public class CustomerService {
	
	private static CustomerService custService;
	
	private CustomerService() {
	}
	
	public static CustomerService getAllCustomers() {
		if (custService == null) {
			custService = new CustomerService();
		}
		return custService;
	}
	
	public List<Customer> getAllCustomerDetails() {
		return CustomerDaoImplementation.getCustDao().getAllCustomers();
	}

	public boolean registerCustomer(Customer cust) {
		return CustomerDaoImplementation.getCustDao().insertCustomer(cust);
	}

	public boolean registerCustomerProcedure(Customer cust) {
		return CustomerDaoImplementation.getCustDao().insertCustomerProcedure(cust);
	}

	public Customer getCustomer() {
		return CustomerDaoImplementation.getCustDao().getCustomer();
	}
}