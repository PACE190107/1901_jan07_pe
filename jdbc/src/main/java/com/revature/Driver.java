package com.revature;

import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args) {
		System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
	}

}
