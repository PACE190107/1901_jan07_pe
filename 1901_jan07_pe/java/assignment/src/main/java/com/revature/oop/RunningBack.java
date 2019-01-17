package com.revature.oop;

public class RunningBack extends Player {
	@Override
	void run() {
		super.run();
		System.out.println("Todd Gurley is really good at running");
	}
	void juking() {
		System.out.println("Barry Sanders is good at juking");
	}
	void juking (int i) {
		System.out.println("Bo Jackson is " + i + "times better than Barry");
	}
	void juking (String s, int i) {
		System.out.println(s + " is " + i + " times better than Barry");
	}
	void juking (int i, String s) {
		System.out.println(s + " is " + i + " times better than Barry");
	}
	void juking (String s, String s2) {
		System.out.println(s + "  is " + s2 + " times better than Barry");
	}
}

