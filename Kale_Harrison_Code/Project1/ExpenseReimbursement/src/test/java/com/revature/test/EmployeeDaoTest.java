package com.revature.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeDaoTest {
	private static final EmployeeDao empEval = new EmployeeDaoImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Test
	public void alreadyTakenUsername() {
		assertFalse(empEval.changeUsername("test", 4));
	}
	
	

}
