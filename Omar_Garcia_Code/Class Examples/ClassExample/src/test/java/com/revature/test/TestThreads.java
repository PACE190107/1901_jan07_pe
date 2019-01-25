package com.revature.test;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.thread.ExtendThreadClass;
import com.revature.thread.ImplementRunnableInterface;

public class TestThreads {

	@Ignore
	public void test1() {
		Thread t1 = new ExtendThreadClass();
		Thread t2 = new ExtendThreadClass();
		
		System.out.println("Test method 1 " + t1.getName()  + ": Name " + t1.getState() + ": status");
		System.out.println("Test method 2 " +  t2.getName()  + ": Name " + t2.getState() + ": status");
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(1000);
			System.out.println("Test method 1 " + t1.getName()  + ": Name " + t1.getState() + ": status");
			System.out.println("Test method 2 " +  t2.getName()  + ": Name " + t2.getState() + ": status");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Thread t1 = new Thread(new ImplementRunnableInterface("thread 1"),"First Thread 1");
		Thread t2 = new Thread(new ImplementRunnableInterface("thread 2"), "Second Thread 2");
		
		System.out.println("Test method 1 " + t1.getName()  + ": Name " + t1.getState() + ": status");
		System.out.println("Test method 2 " +  t2.getName()  + ": Name " + t2.getState() + ": status");
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(1000);
			System.out.println("Test method 1 " + t1.getName()  + ": Name " + t1.getState() + ": status");
			System.out.println("Test method 2 " +  t2.getName()  + ": Name " + t2.getState() + ": status");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
