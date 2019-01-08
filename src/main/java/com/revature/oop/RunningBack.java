package com.revature.oop;

public class RunningBack extends Player {
	@Override
	void run() {
		System.out.println("Running backs are really good at running - even better than quarter backs.");
	}
	
	void juke() {
		System.out.println("Peter Sanders is good at juking.");
	}
	
	void juke(int i) {
		System.out.println("Bo Jackson is " + i + "times better than Peter at juking.");
	}
}
