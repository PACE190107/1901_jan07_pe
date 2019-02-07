package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.Dispatcher.MasterDispatcher;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.model.ResolvedReimbursement;
import com.revature.services.loginService;
import com.revature.services.reimbursementService;
import com.revature.services.reimbursementServiceImpl;

//import com.fasterxml.jackson.databind.ObjectMapper;
public class MasterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRequestURI().contains("logout")) {
			request.getSession().invalidate(); //doesn't work quite right
			request.getRequestDispatcher("/").forward(request,response);
		}
		Object o = MasterDispatcher.process(request, response);
		if(o != null) {
			if(o instanceof Manager) {
				request.getSession().setAttribute("employee", (Employee)o);
				reimbursementService rs = new reimbursementServiceImpl();
				List<Reimbursement> pending =rs.getPendingReimbursement();
				List<ResolvedReimbursement> resolved =rs.getResolvedReimbursements();
				request.getSession().setAttribute("pending", pending);
				request.getSession().setAttribute("resolved", resolved);
				request.getRequestDispatcher("/ManagerPortal.jsp").forward(request, response);
			}else if( o instanceof Employee) {
				//pending and resolved reimbursements for the returned employee
				reimbursementService rs = new reimbursementServiceImpl();
				request.getSession().setAttribute("employee", (Employee)o);
				List<Reimbursement> unfiltered = rs.getEmployeeReimbursements(((Employee)o).getUserName());
				List<Reimbursement> pendings = new ArrayList<>();
				List<ResolvedReimbursement> resolved = new ArrayList<>();
				for (int i = 0; i< unfiltered.size(); i++) {
					if(unfiltered.get(i) instanceof ResolvedReimbursement)
					{resolved.add((ResolvedReimbursement) unfiltered.get(i));}
					else{pendings.add(unfiltered.get(i));}
				}

				request.getSession().setAttribute("pending", pendings);
				request.getSession().setAttribute("resolved", resolved);
				request.getRequestDispatcher("/EmployeePortal.jsp").forward(request, response);
			}
		}else { //goes here with an invalid login. I want to replace the calls with ajax to avoid this and be able to change the text
			request.getRequestDispatcher("/").forward(request,response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}