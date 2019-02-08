package com.revature.tests;

import static org.junit.Assert.assertEquals;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.LoginService;
import com.revature.services.LoginServiceImpl;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;
import org.junit.Test;

public class TestERS 
{
	private static final ReimbursementService reimburseService = new ReimbursementServiceImpl();
	private static final EmployeeService employeeService = new EmployeeServiceImpl();
	private static final LoginService loginService = new LoginServiceImpl();
	private static final EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Test
	public void testEmployeeLogin()
	{
		String username = "emp1";
		String password = "qwerty1";
		
		assertEquals("Employee", employeeDao.employeeLogin(username, password));
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
	
	@Test
	public void test()
	{
		
	}
}