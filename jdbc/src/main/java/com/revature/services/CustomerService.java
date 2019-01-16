package com.revature.services;

import java.util.List;
import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;

public class CustomerService {

	private static CustomerService custService;
	private CustomerService() {
	}
	
	public static CustomerService getCustomerService() {
		if (custService == null) {
			custService = new CustomerService();
		}
		return custService;
	}
	
	public List<Customer> getAllCustomerDetails() {
		return CustomerDaoImplementation.getCustDao().getAllCustomers();
	}
}
