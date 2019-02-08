package com.revature;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.EmployeeType;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.services.ERSService;

import org.apache.log4j.Logger;

public class MasterDispatcher {

	public static ERSService ersService = ERSService.getERSService();
	final static Logger log = Logger.getLogger(MasterDispatcher.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static Object process(HttpServletRequest req, HttpServletResponse resp) {
		
		String reqPath = req.getRequestURI();
		reqPath = reqPath.toLowerCase();
		String[] pathVars = reqPath.split("/");
		boolean reimbursementPath = false;
		boolean employeePath = false;
		boolean loginPath = false;
		int pathIndex = -1;
		
		//determine initial path value and its index
		for(int i = 0; i < pathVars.length; i++) {
			String pVar = pathVars[i];
			
			if(pVar.contains("reimbursement")) {
				reimbursementPath = true;
				pathIndex = i;
				log.info("REIMBURSEMENT HIT");
				break;
			}
			if(pVar.contains("employee")) {
				employeePath = true;
				pathIndex = i;
				log.info("EMPLOYEE HIT");
				break;
			}
			if(pVar.contains("login")) {
				loginPath = true;
				pathIndex = i;	
				log.info("LOGIN HIT");
				break;
			}
		}
		
		//determine the paths corresponding logic
		if(reimbursementPath) {
			if(pathIndex + 1 < pathVars.length) {
				if(pathVars[pathIndex+1].contains("approved") ) {
					
					try {
						
						int rId = -1;
				        Reimbursement standin = mapper.readValue(req.getReader(), Reimbursement.class);
				        rId = standin.getRequestId();
				
						int mId = (int)req.getSession().getAttribute("userId");
						resp.setStatus(200);
						return ersService.resolveReimbursement(ReimbursementStatus.APPROVED, rId, mId);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				if(pathVars[pathIndex+1].contains("denied") ) {
					
					try {
						
						int rId = -1;
				        Reimbursement standin = mapper.readValue(req.getReader(), Reimbursement.class);
				        rId = standin.getRequestId();
						
				
						int mId = (int)req.getSession().getAttribute("userId");
						resp.setStatus(200);
						return ersService.resolveReimbursement(ReimbursementStatus.DENIED, rId, mId);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}else if(pathIndex +1 < pathVars.length) {	
			
				if(pathVars[pathIndex+1].contains("pending") && req.getMethod().equals("GET")) {
					
					log.info("GET ALL PENDING HIT");
					List<Reimbursement> pendingReim = ersService.getPendingReimbursements();
					resp.setStatus(200);
					return pendingReim;
				}
				else if( pathVars[pathIndex+1].contains("resolved") && req.getMethod().equals("GET")) {
					
					log.info("GET ALL RESOLVED HIT");
					List<Reimbursement> resolvedReim = ersService.getResolvedReimbursements();
					resp.setStatus(200);
					return resolvedReim;
				}
				else if(req.getMethod().equals("GET")){
					try {
						int rId = -1;
						rId = Integer.parseInt(pathVars[pathIndex+1]);
						
						log.info("GET BY R ID HIT");
						Reimbursement reimbursement = ersService.getReimbursementById(rId);
						if(reimbursement != null) {
							resp.setStatus(200);
						}else {
							resp.setStatus(404);
						}
						return reimbursement;
					}catch(NumberFormatException e) {
						log.error("NumberFormatException parsing reimbursementPath in MasterDispatcher");
						e.printStackTrace();
					}
				}
			} else {
				
				if(req.getMethod().equalsIgnoreCase("GET")) {
					
					log.info("GET ALL REIMBURSEMENTS HIT");
					
					List<Reimbursement> allReim = ersService.getAllReimbursements();
					resp.setStatus(200);
					return allReim;
				}
				if(req.getMethod().equalsIgnoreCase("POST")) {
//TODO: handle post reimbursement
					log.info("CREATE A REIMBURSEMENT HIT");
					
					boolean executed = false;
					try {
						
				        Reimbursement standin = mapper.readValue(req.getReader(), Reimbursement.class);
				        
						standin.setEmployeeId((int)req.getSession().getAttribute("userId"));
						executed = ersService.createReimbursement(standin);
						}catch(Exception e) {
							e.printStackTrace();
						}
					if(executed) {
						resp.setStatus(200);
					}else {
						resp.setStatus(404);
					}
					return executed;
				}
			}
		}
		
		if(employeePath) {
			if(pathIndex + 1 < pathVars.length && pathVars[pathIndex+1].equalsIgnoreCase("reimbursements")) {
				//get session user reimbursements
				
				log.info("Getting all session user Reimbursements");
				int uId = (int)req.getSession().getAttribute("userId");
				List<Reimbursement> userReimbursements = ersService.getReimbursementsByEmployeeId(uId);
				if(userReimbursements != null) {
					resp.setStatus(200);
				}else {
					resp.setStatus(404);
				}
				return userReimbursements;
			}else if(pathIndex + 1 < pathVars.length && pathVars[pathIndex+1].equalsIgnoreCase("firstName")) {
				//get session user reimbursements
				
				log.info("Update first name");
				int uId = (int)req.getSession().getAttribute("userId");
				String firstName = null;
				try {
					
			        Employee standin = mapper.readValue(req.getReader(), Employee.class);
			        firstName = standin.getFirstName();
					
					}catch(Exception e) {
						e.printStackTrace();
					}
				boolean executed = ersService.updateFirstName(firstName, uId);
				if(executed) {
					resp.setStatus(200);
				}else {
					resp.setStatus(404);
				}
				return executed;
			}else if(pathIndex + 1 < pathVars.length && pathVars[pathIndex+1].equalsIgnoreCase("lastName")) {
				//get session user reimbursements
				
				log.info("Update last name");
				int uId = (int)req.getSession().getAttribute("userId");
				String lastName = null;
				try {
					
			        Employee standin = mapper.readValue(req.getReader(), Employee.class);
			        lastName = standin.getLastName();
					
					}catch(Exception e) {
						e.printStackTrace();
					}
				boolean executed = ersService.updateLastName(lastName, uId);
				if(executed) {
					resp.setStatus(200);
				}else {
					resp.setStatus(404);
				}
				return executed;
			}else if(pathIndex + 1 < pathVars.length && pathVars[pathIndex+1].equalsIgnoreCase("email")) {
				
				
				log.info("Update email");
				int uId = (int)req.getSession().getAttribute("userId");
				String email = null;
				try {
					
			        Employee standin = mapper.readValue(req.getReader(), Employee.class);
			        email = standin.getEmail();
					
					}catch(Exception e) {
						e.printStackTrace();
					}
				boolean executed = ersService.updateEmail(email, uId);
				if(executed) {
					resp.setStatus(200);
				}else {
					resp.setStatus(404);
				}
				return executed;
			}else if(pathIndex + 1 < pathVars.length) {
				try {
					int rId = -1;
					rId = Integer.parseInt(pathVars[pathIndex+1]);
					if(rId == -2) {
						HttpSession session = req.getSession();
						log.info("session: " + session.getAttribute("userId"));
						rId = (int)session.getAttribute("userId");
					}
					log.info("GET EMPLOYEE BY ID HIT");
					
					Employee employee = ersService.getUserById(rId);
					resp.setStatus(200);
					return employee;
				}catch(NumberFormatException e) {
					log.error("NumberFormatException parsing reimbursementPath in MasterDispatcher");
					e.printStackTrace();
				}
			}else if(req.getMethod().equalsIgnoreCase("GET")) {
				
				log.info("GET ALL EMPLOYEES HIT");
				//Only gets Employees, not managers
				resp.setStatus(200);
				List<Employee> allEmpl = ersService.getAllEmployees();
				return allEmpl;
			}else if(req.getMethod().equalsIgnoreCase("POST")) {
//TODO: POST employee
				log.info("POST EMPLOYEE HIT");
				return "post employee";
			}
		}
		
		if(loginPath) {
			
			if(req.getMethod().equalsIgnoreCase("POST")) {
				
				log.info("LOGIN HIT");
				Employee employee = attemptLogin(req);
				if(employee == null) {
					resp.setStatus(404);
				}else {
					resp.setStatus(200);
					if(employee.getEmployeeType() == EmployeeType.MANAGER) {
						resp.setHeader("nextPage", "http://localhost:8080/Project1_ERS/managerHome.html");
					}
					else {
						resp.setHeader("nextPage", "http://localhost:8080/Project1_ERS/employeeHome.html");
					}
				}
				return employee;
			}else if(req.getMethod().equalsIgnoreCase("GET")) {
				
				log.info("LOGOUT HIT");
				log.info("session username: " + req.getSession().getAttribute("username"));
				log.info("session user_id: " + req.getSession().getAttribute("userId"));
				logout(req);
				resp.setStatus(200);
				return true;
			}
		}
		
		resp.setStatus(404);
		return null;
	}
	
	//FOR LOGIN
	protected static Employee attemptLogin(HttpServletRequest req) {
		
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
		
		Employee logEmployee = null;
		Employee employee = null;
		String username = null;
		String password = null;
		if(req.getContentType().equals("application/json")) {
			log.info("Content type application/json");
		}
		try {
			
        logEmployee = mapper.readValue(req.getReader(), Employee.class);
        username = logEmployee.getUsername();
        password = logEmployee.getPassword();
		
		employee = ersService.getUserByUsernamePassword(username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(employee != null) {
			
			HttpSession session = req.getSession();
			session.setAttribute("username", employee.getUsername());
			log.info("setting session attribute userId: " + employee.getEmployeeId());
			session.setAttribute("userId", employee.getEmployeeId());
			log.info("setting session attribute userId: " + session.getAttribute("userId"));
			session.setAttribute("user", employee);
			
		}
		
		return employee;
		
	}
	
	protected static void logout(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		log.info("Logging out and removing session attributes");
		session.removeAttribute("username");
		session.removeAttribute("userId");
		session.removeAttribute("user");
		
	}
}
