package com.revature.tjordan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reavature.tjordan.models.Employee;
import com.revature.delegate.EmployeeDelegate;

import com.revature.delegate.LoginDelegate;
import com.revature.delegate.ManagerDelegate;
import com.revature.delegate.RequestDelegate;

/**
 * @author Timothy Jordan
 * @Purpose Handles control of HttpRequest From the Front Controller
 *
 */
public class Dispatcher {
	private static ManagerDelegate md = new ManagerDelegate();
	private static EmployeeDelegate ed = new EmployeeDelegate();
	private static LoginDelegate ld = new LoginDelegate();
	private static RequestDelegate rd = new RequestDelegate();

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee currentEmployee = (Employee) session.getAttribute("currentEmployee");
		String switchString = req.getRequestURI().substring(req.getContextPath().length() + 1);
		while (switchString.indexOf("/") > 0) {
			switchString = switchString.substring(0, switchString.indexOf("/"));
		}

		switch (switchString) {
		case "updateReq":
			rd.updateRequest(req, resp);
			break;
		case "AllRequest":
			rd.getAll(req, resp);
			break;
		case "getGResolved":
			rd.getGResolved(req, resp);
			break;
		case "getAllPending":
			md.getAllPending(req, resp);
			break;
		case "addEmployee":
			md.addEmployee(req, resp);
			break;
		case "allUsers":
			ed.getAllEmployees(req, resp);
			break;
		case "updateProfile":
			ed.updateProfile(req, resp);
			break;
		case "addRequest":
			rd.addRequest(req, resp);
			break;
		case "getResolved":
			ed.getAllResolved(req, resp);
			break;
		case "getPending":
			ed.getAllPending(req, resp);
			break;
		case "returnHome":
			if (currentEmployee.getIsManager().equals("Y")) {
				md.goHome(req, resp);
			} else {
				ed.goHome(req, resp);
			}
			break;
		case "editProfile":
			if (currentEmployee.getIsManager().equals("Y")) {
				md.editProfile(req, resp);
			} else {
				ed.editProfile(req, resp);
			}

			break;
		case "profileView":
			if (currentEmployee.getIsManager().equals("Y")) {
				md.viewProfile(req, resp);
			} else {
				ed.viewProfile(req, resp);
			}

			break;
		case "manHome":

			md.goHome(req, resp);
			break;

		case "empHome":

			ed.goHome(req, resp);
			break;

		case "login":
			if ("POST".equals(req.getMethod())) {

				ld.login(req, resp);
			} else {
				System.out.println("in login else");
				ld.getPage(req, resp);
			}
			break;
		case "logout":
			ld.logout(req, resp);
			break;

		default:
			break;

		}

	}
}
