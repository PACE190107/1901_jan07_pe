package com.revature.thread;

public class ExtendThreadClass extends Thread {

	@Override
	public synchronized void run() {
		//super.run()
		System.out.println(this.getName() + " :name");
		for (int i = 0;  i < 10; i++) {
			try {
				Thread.sleep(100);
				System.out.println(this.getName() + " " + this.getState() + " :status");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
