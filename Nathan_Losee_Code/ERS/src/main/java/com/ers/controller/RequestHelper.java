package com.ers.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.models.Employee;
import com.ers.services.EmployeeService;
import com.ers.services.LoginService;
import com.ers.services.ManagerService;
import com.ers.util.ConnectionManager;
import com.ers.util.ERSExceptions;

public class RequestHelper {
	private RequestHelper() { }
	private static final Logger log = Logger.getLogger(RequestHelper.class);
	
	public static void processPOST(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];
		
		switch (switchString) {
		case "login":
			log.info("Logging in");
			LoginService.login(req, resp);
			break;
		case "register":
			log.info("Registering new employee");
			ManagerService.registerEmployee(req, resp);
			break;
		case "request":
			log.info("Making reimbursement request");
			EmployeeService.makeRequest(req, resp);
			break;
		case "receipt":
			log.info("Saving reimbursement receipt");
			EmployeeService.saveReceipt(req, resp);
			break;
		case "requestsSingle":
			if (((Employee) req.getSession().getAttribute("user")).isManager()) {
				log.info("Getting reimbursement requests for single employee");
				ManagerService.getReimbursementsSingle(req, resp);
			}
			break;
		case "employee":
			log.info("Getting single employee");
			ManagerService.getEmployee(req, resp);
			break;
		default:
			throw new ERSExceptions.URINotRecognizedException();
		}
	}

	public static void processGET(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];
		
		switch (switchString) {
		case "session":
			log.info("Getting current session information");
			if (req.getSession().getAttribute("user") == null ||
				req.getSession().getAttribute("temp") != null) {
				if (req.getSession().getAttribute("temp") == null) {
					log.info("Directing to login");
					req.getRequestDispatcher("/components/login/login.html").forward(req, resp);
				} else {
					log.info("Logging in");
					LoginService.login(req, resp);
				}
				req.getSession().setAttribute("temp", null);
			}
			else {
				ConnectionManager.setJDBCConnection(
						((Employee) req.getSession().getAttribute("user")).geteUsername(),
						((Employee) req.getSession().getAttribute("user")).getePassword());
				if (((Employee) req.getSession().getAttribute("user")).isManager()) {
					log.info("Directing to manager homepage");
					req.getRequestDispatcher("/components/manager/manager.html").forward(req, resp);
				} else if (((Employee) req.getSession().getAttribute("user")).isConfirmed()) {
					log.info("Directing to employee homepage");
					req.getRequestDispatcher("/components/employee/employee.html").forward(req, resp);
				} else {
					log.info("Directing to email confirmation notification");
					req.getRequestDispatcher("/components/needConfirmation/needConfirmation.html").forward(req, resp);
				}
			}
			break;
		case "username":
			log.info("Getting employee username");
			EmployeeService.getUsername(req, resp);
			break;
		case "email":
			log.info("Getting employee email");
			EmployeeService.getEmail(req, resp);
			break;
		case "alter":
			log.info("Directing to employee settings");
			req.getRequestDispatcher("/components/settings/settings.html").forward(req, resp);
			break;
		case "settings":
			log.info("Getting employee settings");
			EmployeeService.getSettings(req, resp);
			break;
		case "requests":
			if (((Employee) req.getSession().getAttribute("user")).isManager()) {
				log.info("Getting all employee reimbursement requests");
				ManagerService.getReimbursements(req, resp);
			} else {
				log.info("Getting employee reimbursement requests");
				EmployeeService.getReimbursements(req, resp);
			}
			break;
		case "request":
			log.info("Directing to request form");
			req.getRequestDispatcher("/components/request/request.html").forward(req, resp);
			break;
		case "receipt":
			log.info("Directing to receipt viewer");
			req.getRequestDispatcher("/components/loadReceipt/loadReceipt.html").forward(req, resp);
			break;
		case "receiptForm":
			log.info("Directing to receipt form");
			req.getRequestDispatcher("/components/saveReceipt/saveReceipt.html").forward(req, resp);
			break;
		case "receiptPath":
			log.info("Getting reimbursement receipt");
			EmployeeService.loadReceipt(req, resp);
			break;
		case "employees":
			log.info("Getting all employees");
			ManagerService.getEmployees(req, resp);
			break;
		case "registration":
			if (((Employee) req.getSession().getAttribute("user")).isManager()) {
				log.info("Getting employee registration form");
				req.getRequestDispatcher("/components/register/register.html").forward(req, resp);
			}
			break;
		case "ERS":
			log.info("Logging in");
			LoginService.login(req, resp);
			break;
		case "logout":
			log.info("Logging out");
			LoginService.logout(req, resp);
			break;
		default:
			throw new ERSExceptions.URINotRecognizedException();
		}
	}
	
	public static void processPUT(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		String[] switchStrings = req.getRequestURI().split("/");
		String switchString = switchStrings[switchStrings.length - 1];

		switch (switchString) {
		case "request":
			log.info("Stamping approval status for reimbursement request");
			ManagerService.stampRequest(req, resp);
			break;
		case "settings":
			log.info("Updating employee settings");
			EmployeeService.updateSettings(req, resp);
			break;
		default:
			throw new ERSExceptions.URINotRecognizedException();
		}
	}
}
