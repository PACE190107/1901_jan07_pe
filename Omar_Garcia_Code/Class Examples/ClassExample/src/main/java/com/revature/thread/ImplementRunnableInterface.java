package com.revature.thread;

public class ImplementRunnableInterface implements Runnable {
	
	String threadName;
	
	public ImplementRunnableInterface(String threadName) {
		this.threadName = threadName;
		Thread.currentThread().setName(threadName);
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": Name");
		
		for(int i = 0; i < 10; i++) {
			try
			{
				Thread.sleep(10000);
				System.out.print(Thread.currentThread().getName() + " Name ");
				System.out.println(Thread.currentThread().getState() + " State");
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
