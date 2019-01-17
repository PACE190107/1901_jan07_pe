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
		System.out.println("Before Class");
		//first - before the class
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
		//last - after the class
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Set up");
		//second - before every method
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Tear down");
		//fourth - before every method
	}

	@Test
	public void test1() {
		//fail("Not yet implemented");
		System.out.println("test 1");
	}
	
	@Test
	public void test2() {
		System.out.println("test 2");
	}

}
