package com.revature.oop;

public abstract class DeadPool implements TalentedPeople{

	abstract void sarcasm();
	static void regenerate() {
		System.out.println("Deadpool can regenerate");
	}
	
}
