package com.revature;

import com.revature.dao.CustomerDaoImplementation;
import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args) {
		System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());

		//System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());

		//System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(1,"Devante", "Freeman", "free", "free")));
		//System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(2,"Patrick", "Mahomes", "pat", "pat")));
		//System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(3,"Peyton", "Manning", "peyton", "peyton")));		

		//System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
		System.out.println(CustomerService.getCustomerService().getCustomer());		
		System.out.println(CustomerDaoImplementation.getCustDao().getCustomer());
		System.out.println(CustomerService.getCustomerService().registerCustomerProcedure(new Customer(5,"Patrick", "Mahomes", "pat", "pat")));		
	}

}