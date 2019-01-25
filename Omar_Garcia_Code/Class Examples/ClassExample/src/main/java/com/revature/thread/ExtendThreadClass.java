package com.revature.thread;

public class ExtendThreadClass extends Thread{
	
	@Override
	public synchronized void run() {
		System.out.println(this.getName() + ": Name");
		
		for(int i = 0; i < 10; i++) {
			try
			{
				Thread.sleep(100);
				System.out.print(this.getName() + " Name ");
				System.out.println(this.getState() + " State");
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
