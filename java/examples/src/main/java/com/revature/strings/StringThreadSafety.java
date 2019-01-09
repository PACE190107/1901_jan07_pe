package com.revature.strings;


public class StringThreadSafety {
	// static resources to access from threads
	static String a;
	static StringBuilder sbd = new StringBuilder("init");
	static StringBuffer sbf = new StringBuffer("init");

	public static void main(String[] args) {
		// initiate threads
		Thread t1 = new Thread(new Run(1));
		Thread t2 = new Thread(new Run(2));
		t1.start();
		t2.start();
		System.out.println("Threads started");
		try {
			t1.join();
			t2.join();
			System.out.println("Threads finished execution");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("StringBuilder:"+sbd);
		System.out.println("StringBuffer: "+sbf);
	}
	
	static class Run implements Runnable {
		int id; // unique identifier for each thread

		@Override
		public void run() {
			for (int i=0;i<100;i++) {
				sbd.append(" T"+id);
				sbf.append(" T"+id);
			}
		}
		
		public Run(int id) {
			this.id = id;
		}
	}

}