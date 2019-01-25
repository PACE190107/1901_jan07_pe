package com.revature.thread;

public class FirstThread {
	
	public static void main(String[] args) {
		callThread();
	}
	
	private static void callThread() {
		Thread t1 = new Thread();
		t1.start();
		try {
			t1.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("current Thread: " + t1.currentThread());
		System.out.println("Status: " + t1.getState());
	}

}
