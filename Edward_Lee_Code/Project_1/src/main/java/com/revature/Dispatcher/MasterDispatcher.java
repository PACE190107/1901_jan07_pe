package com.revature.Dispatcher;

import com.revature.services.loginServiceImpl;
import com.revature.services.reimbursementServiceImpl;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;
import com.revature.services.loginService;
import com.revature.services.reimbursementService;

public class MasterDispatcher {
	private MasterDispatcher() {}
		
	private static final loginService loginService = new loginServiceImpl();
	private static final reimbursementService reimbursementService = new reimbursementServiceImpl();
	static ObjectMapper mapper = new ObjectMapper();

	public static Object process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getRequestURI());
		if (request.getRequestURI().contains("login")) {
			Employee e;
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			return loginService.attemptLogin(username, password);
	
		}
		else if (request.getRequestURI().contains("newReimbursement")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date occurDate = null;
			try {
				occurDate = format.parse(request.getParameter("occurDate"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			double amount = Double.parseDouble(request.getParameter("amount"));
			Employee e = (Employee) request.getSession().getAttribute("employee");
			Reimbursement r = reimbursementService.newReimbursement(e.getId(), amount, occurDate);
			response.setContentType("application/json");
			String jsonInString = mapper.writeValueAsString(r);
		    response.getWriter().write(jsonInString);
			return response;
		}else if(request.getRequestURI().contains("newstuff")){
			System.out.println("Editing User");
			int emp_id = ((Employee)request.getSession().getAttribute("employee")).getId();
			return reimbursementService.editUser(emp_id, request.getParameter("fname"), request.getParameter("lname"), request.getParameter("email"));
		}else if( request.getRequestURI().contains("getEmployees")) {
			System.out.println("getting all employees");
			List<Employee> e = EmployeeDAOImpl.getEmployeeDao().getAllEmployees();
			response.setContentType("application/json");
			String jsonInString = mapper.writeValueAsString(e);
		    response.getWriter().write(jsonInString);
			return response;
		}else if(request.getRequestURI().contains("getEmployeeReimbursements")) {
			System.out.println("gettingEmployeeReimbursements");
			List<Reimbursement> r =ReimbursementDAOImpl.getReimbursementDAOImpl().getFilteredReimbursements(request.getParameter("uname"));
			List<Reimbursement> pending = new ArrayList<>();
			List<ResolvedReimbursement> resolved = new ArrayList<>();
			for (int i = 0; i< r.size(); i++) {
				if(r.get(i) instanceof ResolvedReimbursement)
				{resolved.add((ResolvedReimbursement) r.get(i));}
				else{pending.add(r.get(i));}
			}
			String jsonInString ="{\"pending\":";
			jsonInString += mapper.writeValueAsString(pending);
			jsonInString += ",\"resolved\":";
			jsonInString += mapper.writeValueAsString(resolved);
			jsonInString +="}";
			response.getWriter().write(jsonInString);
			return response;
		}else if(request.getRequestURI().contains("approve")) {
			System.out.println("APPROVED");
			ResolvedReimbursement r = reimbursementService.approveReimbursement(Integer.parseInt(request.getParameter("rid")),Integer.parseInt(request.getParameter("manId")));
			String jsonInString = mapper.writeValueAsString(r);
			response.setContentType("application/json");
			response.getWriter().write(jsonInString);
		}else if(request.getRequestURI().contains("deny")){
			System.out.println("DENIED");
			ResolvedReimbursement r = reimbursementService.denyReimbursement(Integer.parseInt(request.getParameter("rid")),Integer.parseInt(request.getParameter("manId")));
			String jsonInString = mapper.writeValueAsString(r);
			response.setContentType("application/json");
			response.getWriter().write(jsonInString);
		}
		else {System.out.println("not implementedCorrectly");}
		return response;

	}
}