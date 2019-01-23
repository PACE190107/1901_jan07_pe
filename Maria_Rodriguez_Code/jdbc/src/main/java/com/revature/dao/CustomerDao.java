package com.revature.dao;

import java.util.List;
import com.revature.models.Customer;

public interface CustomerDao<getBalance> {

	public boolean insertCustomer(Customer cust);
	public boolean insertCustomerProcedure(Customer cust);
	public Customer getCustomer( String userName, String passWord );
	public List<Customer> getAllCustomers();
	public boolean getsSuperUser( String userName, String passWord );
	public double getBalance(String user_id);
	public boolean updateCustomer(String id, String firstName, String lastName);
	public boolean  withdraw(String user_id, double withdrawAmount);
	public boolean deposit(String user_id, double withdrawAmount);
	public boolean createBankAccount(int accountBalance, double accountNumber, String firstName, String lastName);
	public double balanceCheck(String user_id);
	public boolean deleteAccount(String user_id);
	public boolean updateUser(String user_id, String firstName, String lastName);
	public boolean deleteUser(String user);
	//public double returnBalance(String user_ID);
}
