package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCalculator {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addTwoValues(){
		assertEquals(30,new calculator().add(10,20));
	}
	
	@Test
	public void failingTestForAdd() {
		assertFalse(232323 == new calculator().add(10,20));
	}

}
