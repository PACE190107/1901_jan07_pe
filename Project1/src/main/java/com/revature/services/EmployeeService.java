package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EmployeeService {

	Object viewInformation(HttpServletRequest request, HttpServletResponse response);
	Object viewResolvedRequests(HttpServletRequest request, HttpServletResponse response);
	Object viewPendingRequests(HttpServletRequest request, HttpServletResponse response);
	Object submitReimbursement(HttpServletRequest request, HttpServletResponse response);
	Object updateInformation(HttpServletRequest request, HttpServletResponse response);

}
