package com.revature.test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;

public class ReimbursementDaoTest {
	private static final ReimbursementDao rebEval = new ReimbursementDaoImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Test
	public void descriptionTooLong() {
		assertFalse(rebEval.submitRequest(32, "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 1));
	}
	
	
}
