package com.proj0.services;

import java.util.List;

import com.proj0.dao.CustomerDaoImplementation;
import com.proj0.models.Customer;

public class CustomerService {
	private static CustomerService custService;
	private CustomerService(){
		
	}
	
	public static CustomerService getCustomerService() {
		if(custService == null) {
			custService = new CustomerService();
		}
		return custService;
	
	}
	
	public List<Customer> getAllCustomerDetails(){
		return CustomerDaoImplementation.getCustDao().getAllCustomers();
	}
	
	public boolean registerCustomer(Customer cust) {
		return CustomerDaoImplementation.getCustDao().insertCustomer(cust);
	}
	
	public boolean registerCustomerProcedure(Customer cust) {
		return CustomerDaoImplementation.getCustDao().insertCustomerProcedure(cust);
	}
	
	public Customer getCustomer(String cust) {
		return CustomerDaoImplementation.getCustDao().getCustomer(cust);
	}
	
	public boolean deposit(int depositWithdraw, String username, int id) {
		return CustomerDaoImplementation.getCustDao().depositWithdraw(depositWithdraw, username, id);
	}
	
 }
