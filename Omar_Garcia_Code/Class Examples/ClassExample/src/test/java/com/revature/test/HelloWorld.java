package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloWorld {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("test before - class");

		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("test after - class");

	}

	@Before
	public void setUp() throws Exception {
		System.out.println("test Up - method");

	}

	@After
	public void tearDown() throws Exception {
		System.out.println("test Down - method");

	}

	@Test
	public void test1() {
		System.out.println("test 1");
	}
	
	@Test
	public void test2() {
		System.out.println("Test 2");
	}

}
