package com.revature.oop;

public class QuarterBack extends Player{

	@Override
	void toss() {
		System.out.println("QB's are good at tossing");
	}
	
	@Override
	void run() {
		//super.run();
		System.out.println("QB's are good at running");
	}
	
	void audible() {
		System.out.println("QB's are audible");
	}
	
	void sliding() {
		System.out.println("QB's are good at sliding");
	}
	
	public static void main(String[] args) {
		//reference type, reference variable 
		//new constructor
		Player p1 = new QuarterBack();
		Player p2 = new Player();
		QuarterBack qb1 = new QuarterBack();
		//QuarterBack qb2 = new Player();
		//not is-a relationship
//		p1.jump();
//		p1.run();
//		p1.toss();
//		p1.receive();
		p2.jump();
		p2.toss();
		p2.receive();
		p2.run();
		
//		qb1.jump();
//		qb1.toss();
//		qb1.run();
//		qb1.receive();
//		qb1.audible();
//		qb1.sliding();
	}
}
