package com.revature.checker;

import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;

public class Checker {

	
	public static void main(String[] args) {
		
		EmployeeService.getEmployeeService().viewAllEmployees();
		
		System.out.println(EmployeeService.getEmployeeService().viewSingleEmployee(1));
		
		System.out.println(EmployeeService.getEmployeeService().viewAllEmployees());
		
		System.out.println(EmployeeService.getEmployeeService().changeUsername("HarryTest", 1));
		
		System.out.println(EmployeeService.getEmployeeService().changePassword("HarryPassword", 1));
		
		System.out.println(EmployeeService.getEmployeeService().changeEmail("Harry@Gmail.com", 1));
		
		//System.out.println(ReimbursementService.getRebService().submitRequest(109, "larger amount", 1));
		
		System.out.println(ReimbursementService.getRebService().viewPending(1));
		
		System.out.println(ReimbursementService.getRebService().viewResolved(1));
		
		//System.out.println(ReimbursementService.getRebService().denyRequest(1026, 3));
		
		System.out.println(ReimbursementService.getRebService().viewRequestsSingleEmployee(1));
		
		
		

	}

}
