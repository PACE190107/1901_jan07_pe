package com.revature;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.services.ERSService;

public class TestMain {

	public static ERSService ersService = ERSService.getERSService();
	
//	public static void main(String[] args) {
		
//		Employee employee = new Employee();
//		employee.setEmail("test@email.com");
//		employee.setEmployeeType(EmployeeType.EMPLOYEE);
//		employee.setFirstName("Ty");
//		employee.setLastName("Georgia");
//		employee.setUsername("tgeorgia");
//		employee.setPassword("password");
//		ersService.createEmployee(employee);
		
		//test GetEmployeeById
		
//	Employee employee = ersService.getUserById(2);
//	if(employee != null) {
//	System.out.println("email: " + employee.getEmail());
//	System.out.println("username : " + employee.getUsername());
//	System.out.println("password : " + employee.getPassword());
//	System.out.println("firstname : " + employee.getFirstName());
//	System.out.println("last name : " + employee.getLastName());
//	System.out.println("occuaption : " + employee.getEmployeeType());
//	}
//	
//	//test getUserByUsernamePassword()
//		Employee employee = ersService.getUserByUsernamePassword("holla", "password");
//		if(employee != null) {
//		System.out.println("email: " + employee.getEmail());
//		System.out.println("username : " + employee.getUsername());
//		System.out.println("password : " + employee.getPassword());
//		System.out.println("firstname : " + employee.getFirstName());
//		System.out.println("last name : " + employee.getLastName());
//		System.out.println("occuaption : " + employee.getEmployeeType());
//		}
//		else {
//			System.out.println(employee);
//		}
		
		//tested
//		List<Employee> users = ersService.getAllEmployees();
//		for(Employee e : users) {
//			if(e != null) {
//				System.out.println("email: " + e.getEmail());
//				System.out.println("username : " + e.getUsername());
//				System.out.println("password : " + e.getPassword());
//				System.out.println("firstname : " + e.getFirstName());
//				System.out.println("last name : " + e.getLastName());
//				System.out.println("occupation : " + e.getEmployeeType());
//				}
//				else {
//					System.out.println(e);
//				}
//			
//		}
		
//		Reimbursement createUserReimb2 = new Reimbursement( 1, 220, "T2_subject", 
//				"T2_description");
//		ersService.createReimbursement(createUserReimb2);
//		
//		Reimbursement createUserReimb3 = new Reimbursement( 1, 230, "T3_subject", 
//				"T3_description");
//		ersService.createReimbursement(createUserReimb3);
//		
//		Reimbursement createUserReimb4 = new Reimbursement( 1, 240, "T4_subject", 
//				"T4_description");
//		ersService.createReimbursement(createUserReimb4);
//		
//		Reimbursement createUserReimb5 = new Reimbursement( 1, 250, "T5_subject", 
//				"T5_description");
//		ersService.createReimbursement(createUserReimb5);
		
//		boolean resolved = ersService.resolveReimbursement(ReimbursementStatus.DENIED, 3, 2);
//		System.out.println(resolved);
		
//		List<Reimbursement> rList = ersService.getAllReimbursements();
//		for(Reimbursement r : rList) {
//			System.out.println("ReimbursementSubject: " + r.getSubject());
//		}
//		Reimbursement r = ersService.getReimbursementById(2);
//	System.out.println("ReimbursementSubject: " + r.getSubject());
	
//		String testURI = "localHost:8080/rest/a/b/c/d";
//		String[] testPathValues = testURI.split("rest/");
//		for(String pathVar : testPathValues) {
//			System.out.println("Path Variable : " + pathVar );
//		}
//	}
}
