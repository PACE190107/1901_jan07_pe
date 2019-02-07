package com.revature.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class EmployeeService {
	
	private static EmployeeService empService;
	private final ObjectMapper mapper = new ObjectMapper();
	
	public static EmployeeService getEmployeeService() {
		if (empService == null) {
			empService = new EmployeeService();
		}
		return empService;
	}

	public Object process(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			String[] path = request.getRequestURI().split("/");
			if (path.length == 5 && request.getRequestURI().contains("Single")) {
				//User can view user details 
				//http://localhost:8080/ExpenseReimbursement/rest/employee/Single
				try {
				HttpSession session = request.getSession();
				int e_id = (int) session.getAttribute("e_id");
					return viewSingleEmployee(e_id);
				}catch(NullPointerException e) {
					return "Invalid Login";
				}
				
			}
			if (path.length == 5 && request.getRequestURI().contains("All")) {
				//Manager can view all employees
				//http://localhost:8080/ExpenseReimbursement/rest/employee/All
				return viewAllEmployees();
			}
		}
		if (request.getMethod().equals("POST")) {
			if (request.getRequestURI().contains("logout")) {
				try {
					EmployeeDaoImpl.getEmpDao().logout(request, response);
					String loginUrl = "http://localhost:8080/ExpenseReimbursement/html/Login.html";
					response.setHeader("Location", loginUrl);
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (request.getHeader("Content-Type").equals("application/json")) {
				try {
					Employee logEmployee = null;
					logEmployee = mapper.readValue(request.getReader(), Employee.class);
					final String username = logEmployee.getE_username();
					final String password = logEmployee.getE_password();
					
					Employee attempting = attemptAuthentication(username, password);
					if (attempting != null) {
						HttpSession session = request.getSession();
						session.setAttribute("e_username", attempting.getE_username());
						session.setAttribute("e_id", attempting.getE_id());
						String url = "http://localhost:8080/ExpenseReimbursement/html/Home.html";
						String managerUrl = "http://localhost:8080/ExpenseReimbursement/html/ManagerHome.html";
						if (attempting.isManager() == 1) {
							response.setHeader("Location", managerUrl);
							return attempting;
						}
						response.setHeader("Location", url);
						return attempting;
					}else
						return null;
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
				}
		
		if (request.getMethod().equals("PUT")) {
			if (request.getHeader("Content-Type").equals("application/json")) {
				if (request.getRequestURI().contains("Username")) {
					try {
						//Employee can change username
						//http://localhost:8080/ExpenseReimbursement/rest/employee/Username
						HttpSession session = request.getSession();
						
						Employee updateName = mapper.readValue(request.getReader(), Employee.class);
						String e_username = updateName.getE_username();
						int e_id = (int) session.getAttribute("e_id");
						return changeUsername(e_username, e_id);
					}catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (request.getRequestURI().contains("Password")) {
					try {
						//Employee can change Password
						//http://localhost:8080/ExpenseReimbursement/rest/employee/Password
						Employee updatePass = mapper.readValue(request.getReader(), Employee.class);
						String e_password = updatePass.getE_password();
						HttpSession session = request.getSession();
						int e_id = (int) session.getAttribute("e_id");
						return changePassword(e_password, e_id);
					}catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (request.getRequestURI().contains("Email")) {
					try {
						//Employee can change Email
						//http://localhost:8080/ExpenseReimbursement/rest/employee/Email
						Employee updateEmail = mapper.readValue(request.getReader(), Employee.class);
						String e_email = updateEmail.getE_email();
						HttpSession session = request.getSession();
						int e_id = (int) session.getAttribute("e_id");
						return changeEmail(e_email, e_id);
					}catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		return null;
	}
	
	public Employee viewSingleEmployee(int e_id) {
		return EmployeeDaoImpl.getEmpDao().viewSingleEmployee(e_id);
	}
	
	public List<Employee> viewAllEmployees() {
		return EmployeeDaoImpl.getEmpDao().viewAllEmployees();
		
	}

	public boolean changeUsername(String e_username, int e_id) {
		return EmployeeDaoImpl.getEmpDao().changeUsername(e_username, e_id);
	}
	public boolean changePassword(String e_password, int e_id) {
		return EmployeeDaoImpl.getEmpDao().changePassword(e_password, e_id);
	}
	
	public boolean changeEmail(String e_email, int e_id) {
		return EmployeeDaoImpl.getEmpDao().changeEmail(e_email, e_id);
	}
	
	public Employee attemptAuthentication(String username, String password) {
		return EmployeeDaoImpl.getEmpDao().attemptAuthentication(username, password);
	}
	
	//**********************************************************************
	

}
