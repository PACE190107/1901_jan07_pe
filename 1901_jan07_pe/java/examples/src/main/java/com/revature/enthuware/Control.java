package com.revature.enthuware;

public class Control {
	
	//OCA
	private int switchStatement(int i) {
		switch(i) {
		case 1: 
			System.out.println("1");
		case 2:
			System.out.println("2");
			break;
			//return 5; // -> acts as the same
		case 3:
			System.out.println("3");
			break;
		default:
			//If option is not covered.
			System.out.println("Nope");
		}
		return 0;
	}
	
	private void switchTest() {
		char c = 5;
		Character ch = c;
		System.out.println(ch.charValue());
		switch(c) {
		case '':
			System.out.println("hello");
			break;
//		case 5:
//			System.out.println();
//		it complains because the are the same.
		case '5':
			System.out.println("This doesn't print");
			break;
		}
		
		//OCA
		boolean bool = true;
//		switch(bool) {
//		
//		} NO!
		
		long l = 50;
//		switch(l) {
//		
//		} NO!
		
		byte b = 5;
		switch(b) {
		
		}
	}
	
	public void letsLoop() {
		//OCA
		System.out.println(5 + 5 + "Hello");
		
		//First part is init
		//Second part is condition
		//Third
		System.out.println("First for loop");
		for(int i = 0; i < 5; i++) {
			System.out.println(i);
		}
		
		//Slightly more complex
		//for(int i = 0; i < 7; System.out.println(i++), System.out.println("Hey look")) {
		System.out.println("Second for loop");
		for(int i = 0; i < 7; System.out.println(i++)) {	
			
		}
		
		//Infinite
//		for(;;) {
//			
//		}
		
		//Daemon
//		while(true) {
//			
//		}
		
		System.out.println("While");
		int l = 0;
		while(l < 3) {
			//Play around wiht continue.
			if(l == 2) {
				continue;
			}
			System.out.println(l++);
		}
			
	}
	
	public static void main(String[] args) {
		Control control = new Control();
		
		System.out.println("Try number 1");
		control.switchStatement(1); // -> 1,2
		System.out.println("Try number 2");
		control.switchStatement(3); // -> 3
		System.out.println("Try number 3");
		control.switchStatement(0); // -> Nope
		
		control.switchTest();
		
		System.out.println("Lets loop");
		control.letsLoop();
		System.out.println("We didnt get here?");
	}
}