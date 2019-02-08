package com.revature.test;


//main method is within this import
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.thread.ExtendThreadClass;

public class TestThreads {

	@Test()
	@Ignore
	public void test() {
		Thread t1 = new ExtendThreadClass();
		Thread t2 = new ExtendThreadClass();
		System.out.println("thread 1: " + t1.getState());
		System.out.println("thread 2: " + t2.getState());
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000);
			System.out.println("thread 1: " + t1.getState());
			System.out.println("thread 2: " + t2.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread 1: " + t1.getState());
		System.out.println("thread 2: " + t2.getState());
	}
	
	@Test
	public void test2() {
		Thread t1 = new Thread(new ImplementRunnableInterface("One"));
		Thread t2 = new Thread(new ImplementRunnableInterface("Two"));
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(20000);
			System.out.println("thread 1: " + t1.getState());
			System.out.println("thread 2: " + t2.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("thread 1: " + t1.getState());
		System.out.println("thread 2: " + t2.getState());
 	}
	
		
		//t3.join(10)
	}

