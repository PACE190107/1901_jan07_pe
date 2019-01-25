package com.revature.oop;

public class TestPlayer {
	
	//has-a relationship (aggregation)
	public static void main(String[] args) {
	RunningBack rb1 = new RunningBack();
	rb1.run();
	rb1.juking();
	rb1.juking(1);
	rb1.juking("Ezekiel Elliot", 2);
	rb1.juking(2,"Ezekiel Elliot");
	rb1.juking("Ezekiel Elliot","two");
	}
}
