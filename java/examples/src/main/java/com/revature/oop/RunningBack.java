package com.revature.oop;

public class RunningBack extends Player {

	@Override
	void run() {
		super.run();
		System.out.println("Todd Gurley - is real good in running");
	}
	
	void juking(){
		System.out.println("Barry Sanders is good at juking");
	}
	
	void juking(int i) {
		System.out.println("Bo Jackson is better " + i + " times than Barry");
	}
	
	void juking(String s, int i) {
		System.out.println(s + " is " + i + " times better than Bo ");
	}
	
	void juking(int i, String s) {
		System.out.println(s + " is better than Bo " + i + " times");
	}

	void juking(String s1, String s2) {
		System.out.println(s1 + " is better than Bo " + s2 + " times");
	}
	
}
