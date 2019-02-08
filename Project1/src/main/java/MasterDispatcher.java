

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImpl;
import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImpl;

public class MasterDispatcher {
private MasterDispatcher() {}
	
	// Command and Mediator Design Pattern
	
	private static final LoginService loginService = new LoginServiceImpl();

	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final ManagerService managerService = new ManagerServiceImpl();
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("masterDispatcher works");

		if(request.getRequestURI().contains("users")) {
			System.out.println("entering users");
			return loginService.attemptAuthentication(request, response);
		} else if(request.getRequestURI().contains("info")) {
			System.out.println("entering viewinformation");
			return employeeService.viewInformation(request, response);
		}else if(request.getRequestURI().contains("pendRequest")) {
			System.out.println("entering viewpendingrequests");
			return employeeService.viewPendingRequests(request, response);
		}else if(request.getRequestURI().contains("compRequest")) {
			System.out.println("entering viewcompletedrequests");
			return employeeService.viewResolvedRequests(request, response);
		}else if(request.getRequestURI().contains("subReim")) {
			System.out.println("entering submitrequests");
			return employeeService.submitReimbursement(request, response);
		}else if(request.getRequestURI().contains("allPendRequest")) {
			System.out.println("entering AllPendingRequests");
			return managerService.viewAllPending(request, response);
		}else if(request.getRequestURI().contains("allResolvedRequest")) {
			System.out.println("entering AllResolvedRequests");
			return managerService.viewAllResolved(request, response);
		}else if(request.getRequestURI().contains("allEmployees")) {
			System.out.println("entering AllEmployees");
			return managerService.viewAllEmployees(request, response);
		}else if(request.getRequestURI().contains("singleempRequest")) {
			System.out.println("entering singleEmployeeRequests");
			return managerService.viewReimSingleEmployee(request, response);
		}
		else {
		return "Not yet Implemented";
		}
	}
}
