package com.revature.oop;

public abstract class DeadPool implements TalentedPerson {
	abstract void sarcasm();
	static void regenerate() {
		System.out.println("Deadpool can regenerate.");
	}
}
