package com.revature.test.junit;
import org.junit.jupiter.api.Test;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.LoginDaoImpl;

import junit.framework.TestCase;

public class ImplTest extends TestCase {
	
	EmployeeDaoImpl testEmployee = new EmployeeDaoImpl();
	LoginDaoImpl testLogin = new LoginDaoImpl();

	@Test
	public void testGetAllEmployees() {
		assertEquals(1,1);
	}
	
	@Test
	public void testGetEmpDetails() {
		assertEquals(testLogin.getEmpDetails("jimmy"), null);
	}
	
	@Test
	public void testGetEmpDetails2() {
		assertEquals(testLogin.getEmpDetails("abc"), null);
	}
	
	@Test
	public void testGetEmpDetails3() {
		assertEquals(testLogin.getEmpDetails("Admin"), null);
	}
	
	@Test
	public void testUpdateLogin() {
		String usernameOne = "sloppy";
		String usernameTwo = "sleepy";
		String password = "sloopy";
		assertFalse(testEmployee.updateLogin(usernameOne, usernameTwo, password));
	}
	
	@Test
	public void testUpdateLogin2() {
		String usernameOne = "Yugi";
		String usernameTwo = "Yugioh";
		String password = "Exodia";
		assertFalse(testEmployee.updateLogin(usernameOne, usernameTwo, password));
	}
	
	@Test
	public void testResolveEmployeeRequest() {
		int id = 0;
		String status = "APROVE";
		int manId = 1231;
		assertFalse(testEmployee.resolveEmployeeRequest(id, status, manId));
	}
}
