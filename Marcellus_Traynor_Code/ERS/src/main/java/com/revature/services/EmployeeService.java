package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EmployeeService 
{
	public void updateInfo(HttpServletRequest request, HttpServletResponse response);
	public void getEmployeeInfo(HttpServletRequest request, HttpServletResponse response);
}