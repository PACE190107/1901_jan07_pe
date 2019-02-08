package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ManagerService {

	Object approveDeny(HttpServletRequest request, HttpServletResponse response);
	Object viewAllPending(HttpServletRequest request, HttpServletResponse response);
	Object viewAllResolved(HttpServletRequest request, HttpServletResponse response);
	Object viewAllEmployees(HttpServletRequest request, HttpServletResponse response);
	Object viewReimSingleEmployee(HttpServletRequest request, HttpServletResponse response);

}
