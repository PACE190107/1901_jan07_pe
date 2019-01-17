package com.revature.exceptions;

public class Main {
	
	public static void main(String[] args) {
		try {
			new Main().riskyBehavior();
		} catch (HankException e) {
			e.printStackTrace();
		}
	}
	
	public void riskyBehavior() throws HankException{
		System.out.println("Before exception");
		throw new HankException();
		//exception disrupts flow - line does not occur
		//System.out.println("After exception");
	}
}
