package com.revature.test;

public class ImplementRunnableInterface implements Runnable {
	String threadName;
	
	public ImplementRunnableInterface (String threadName) {
		this.threadName = threadName;
		System.out.println("constructor " + Thread.currentThread().getName() + " " + Thread.currentThread().getState());
	}
	@Override
	public synchronized void run() {
		//super.run()
		System.out.println(Thread.currentThread().getName() + " :name");
		for (int i = 0;  i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println("Run " + Thread.currentThread().getName() + " " + Thread.currentThread().getState());
			}
			catch  (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}