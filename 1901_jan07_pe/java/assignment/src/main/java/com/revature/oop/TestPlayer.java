package com.revature.oop;

public class TestPlayer {
	//has-a relationship (aggregation:  not is-a - not inheritance)
	public static void main(String[] args) {
		RunningBack RB1 = new RunningBack();
		RB1.run();
		RB1.juking();
		RB1.juking(2);
		RB1.juking("Ezekiel Elliot", 2);
		RB1.juking(2, "Ezekiel Elliot");
		RB1.juking("Ezekiel Elliot","two");
	}
}
