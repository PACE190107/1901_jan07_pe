import org.apache.log4j.BasicConfigurator;

import app.ClientSide;

public class Driver {

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		ClientSide start = new ClientSide();
		start.createOrNot();
		
		
//		System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
//		
//		System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(1,"Devante","Freeman","free","free")));
//		System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(2,"Patrick","Mahomes","pat","pat")));
//		System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(3,"Payton","Manning","peyton","peyton")));
//
//		System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
//		System.out.println(CustomerService.getCustomerService().getCustomer());
//		System.out.println(CustomerDaoImplementation.getCustDao().getCustomer("lsantibanez7"));
//		
		
		

	}

}
