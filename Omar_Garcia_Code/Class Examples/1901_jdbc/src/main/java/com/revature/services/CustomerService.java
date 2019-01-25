package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;

public class CustomerService {

	private static CustomerService custserv;
	
	private CustomerService() {
		
	}
	
	public static CustomerService getCustomerService() {
		if(custserv == null) {
			custserv = new CustomerService();
		}
		return custserv;
	}
	
	public List<Customer> getAllCustomerDetails() throws SQLException {
		return CustomerDaoImplementation.getCustomerImplementation().getAllCustomer();
	}
	
	public boolean registerCustomer(Customer cust) {
		return CustomerDaoImplementation.getCustomerImplementation().insertCustomer(cust);
	}
	
	public Customer getCustomer() throws SQLException {
		return CustomerDaoImplementation.getCustomerImplementation().getCustomer();
	}
}
