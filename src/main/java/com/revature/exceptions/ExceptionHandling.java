package com.revature.exceptions;

public class ExceptionHandling {
	
	public static void main(String[] args) {
		//System.out.println(divideByZero(10,2));
		//System.out.println(divideByZero(10,0));
		//createNullPointerException();
		//try finally example
		try {
			createNumberFormatException();
		} finally {
			System.out.println("everything's cool");
		}
	}
	
	public static int divideByZero(int x, int y) {
		int z = 0;
		int i[] = {1,2,3,4,5};
		for (int j: i) {
			System.out.println("values of i " + j);
		}
//		System.out.println(i[0] +  ": 0");
//		System.out.println(i[4] +  ": 4");
//		System.out.println(i[5] +  ": 5");
//		System.out.println(i[6] +  ": 6");
		try {
			z= x / y;
		} 
		//multiple catch blocks
		catch (ArithmeticException a) {
//			a.getMessage();
//			a.printStackTrace();  //read stacktrace from bottom-up
//			a.getStackTrace();
			System.out.println("Something unexpected happened.");
		} catch (ArrayIndexOutOfBoundsException  ai) {
			System.out.println("What's wrong with my array?");
		}
		return z;
	}
	
	public static void createNullPointerException() {
		String s = null;
		try {
			s = "asdfasdf";
			System.out.println(s.lastIndexOf(1));
		} 
		//multiple 
		catch (NullPointerException | StringIndexOutOfBoundsException str) {
			System.out.println("I wasn't expecting this");
		}	finally {
			System.out.println("this line will always execute");
		}
		
		//System.out.println(s.length());
	}
	
	public static void createNumberFormatException() throws NumberFormatException {
		String s1 = "The early bird catches the worm";
		int i = 10;
		int j = 20;
		i = 30;
		i = Integer.parseInt(s1);
		//use a wrapper
	}
}
