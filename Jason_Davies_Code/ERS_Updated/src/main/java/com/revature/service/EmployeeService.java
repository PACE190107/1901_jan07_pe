package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;

public interface EmployeeService {
	public Employee login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	public Employee logout(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public Employee register(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public Employee updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public Employee getEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException;
	public ArrayList<Employee> getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
