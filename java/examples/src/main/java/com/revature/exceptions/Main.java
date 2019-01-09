package com.revature.exceptions;
public class Main {
	public static void main(String[] args) {
		try {
			new Main().riskyBehavior();
		} 
		catch (HankException e) {
			e.printStackTrace();
		}
	}
	public void riskyBehavior() throws HankException{
		System.out.println("before exception");
		throw new HankException();
		//line of code will not get executed
		//System.out.println("after exception");
	}
}
