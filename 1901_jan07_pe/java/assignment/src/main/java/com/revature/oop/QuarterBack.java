package com.revature.oop;

public class QuarterBack extends Player {
	
	@Override
	void toss() {
		System.out.println("QB's are good at tossing");
	}
	@Override
	void run() {
		System.out.println("QB's are good at running");
	}
	void audible () {
		System.out.println("QB's can call audibles");
	}
	void sliding() {
		System.out.println("QB's are good at sliding");
	}
	public static void main(String[] args) {
		//Player = reference type, p1 = reference variable
		//new constructor
		Player p1 =  new QuarterBack();  //QB is a player
		Player p2 = new Player(); 		//player is a player
		QuarterBack qb1 = new QuarterBack();   //QB is a QB
		// not all players are QBs  QuarterBack qb2 = new Player();
//		p1.jump();
//		p1.run();
//		p1.toss();
//		p1.receive();

//		p2.jump();
//		p2.run();
//		p2.toss();
//		p2.receive();
		
		qb1.jump();
		qb1.run();
		qb1.toss();
		qb1.receive();
		qb1.audible();
		qb1.sliding();
	}
}
