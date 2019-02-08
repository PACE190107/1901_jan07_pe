package com.revature.thread;

public class FirstThread {

		public static void main(String[] args) {
			callThread();
		}

		private static void callThread() {
			Thread t1 = new Thread();
			t1.start();
			for(int i=0; i<10; i++) {
				try {
					Thread.sleep(5000);
					System.out.println("current thread: " + t1.currentThread());
					System.out.println("status: " + t1.getState());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
