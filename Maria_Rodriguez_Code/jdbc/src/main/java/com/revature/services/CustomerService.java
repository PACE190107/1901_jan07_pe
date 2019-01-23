package com.revature.services;

import java.util.List;
import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;

public class CustomerService {

	private static CustomerService custService;
	private CustomerService() {
	}
	
	public static CustomerService getCustomerService() {
		if ( custService == null ) {
			custService = new CustomerService();
		}
		return custService;
	}
	
	public List<Customer> getAllCustomers() {
		return CustomerDaoImplementation.getCustDao().getAllCustomers();
	}
	
	public boolean registerCustomer( Customer cust ) {
		return CustomerDaoImplementation.getCustDao().insertCustomer( cust );
	}
	
	public boolean registerCustomerProcedure( Customer cust ) {
		return CustomerDaoImplementation.getCustDao().insertCustomerProcedure( cust );
	}
	
	public Customer getCustomer( String userName, String passWord ) {
		return CustomerDaoImplementation.getCustDao().getCustomer( userName, passWord );
	}
	public  double getBalance(String user_id) {
		return CustomerDaoImplementation.getCustDao().getBalance( user_id ) ;
	}
	
	public boolean updateCustomer (String id, String firstName, String lastName) {
		return CustomerDaoImplementation.getCustDao().updateCustomer( id,firstName,lastName );
	
	}
	
	public double balanceCheck(String user_id) {
		return CustomerDaoImplementation.getCustDao().balanceCheck(user_id);
	}
	public boolean withdraw (String user_id, double withdrawAmount) {
		return CustomerDaoImplementation.getCustDao().withdraw(user_id, withdrawAmount);
	}
	
	public boolean deposit(String user_id, double depositAmount) {
		return CustomerDaoImplementation.getCustDao().deposit(user_id, depositAmount);
	}

	public boolean createBankAccount(int accountBalance, double accountNumber, String firstName, String lastName) {
		return CustomerDaoImplementation.getCustDao().createBankAccount(accountBalance, accountNumber, firstName, lastName);
	}
	public boolean deleteAccount(String user_id) {
		return CustomerDaoImplementation.getCustDao().deleteAccount(user_id);
	}
	public boolean updateUser(String user_id, String firstName, String lastName) {
		return CustomerDaoImplementation.getCustDao().updateUser(user_id, firstName, lastName);
	}
	
	public boolean deleteUser(String user) {
		return CustomerDaoImplementation.getCustDao().deleteUser (user);
	}
	
}



