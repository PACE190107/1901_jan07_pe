import java.sql.SQLException;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		//System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
		System.out.println(CustomerService.getCustomerService().registerCustomer(new Customer(4,"Patrick","Mahomes","pat","pat")));
		//System.out.println(CustomerService.getCustomerService().getAllCustomerDetails());
	}
}
