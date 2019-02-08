package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Employee;

public interface EmployeeService 
{
	public Employee updateInfo(HttpServletRequest request, HttpServletResponse response);
	public List<Employee> getEmployeeInfo(HttpServletRequest request, HttpServletResponse response);
	public List<Employee> getAllEmployeeInfo(HttpServletRequest request, HttpServletResponse response);
}