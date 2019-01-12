package com.revature.exceptions;

public class ExceptionHandling {

	public static void main(String[] args) {
		//System.out.println(divideByZero(10, 2));
		//System.out.println(divideByZero(10, 0));
		//createNullPointerException();
		//try finally - example 
		try {
			createNumberFormatException();
		} finally {
			System.out.println("everything's cool");
		}
	}
	
	public static int divideByZero(int x, int y){
		int z = 0;
		int i[] = {23,34,54,67,78};
//		System.out.println(i[4] + ": 4");
//		System.out.println(i[5] + ": 5");
//		System.out.println(i[6] + ": 6");
		//multiple catch blocks
		try {
			z = x / y;
			for (int j: i) {
				System.out.println("values of i " + j);
			}
		} catch (ArithmeticException a) {
			//a.getMessage();
			a.printStackTrace();
			//a.getStackTrace();
			System.out.println("something unexpected happened");
		} catch (ArrayIndexOutOfBoundsException ai) {
			System.out.println("what's wrong with my array???");
		}
		return z;
	}
	
	public static void createNullPointerException(){
		String s = null;
		//try catch and finally example
		try {
			s = "asdfasdfasdfsad";
			System.out.println(s.indexOf(100));
		} 
		//multiple exceptions in one catch block
		catch (NullPointerException | StringIndexOutOfBoundsException str) {
			System.out.println("I wasn't expecting this");
		}
		finally {
			System.out.println("this line will always be executed");
		}
		//System.out.println(s.length());
	}
	
	public static void createNumberFormatException() throws NumberFormatException {
		String s1 = "early bird catches the worm";
		int i = 10;
		int j = 20;
		i = 30;
		i = Integer.parseInt(s1);
		//wrapping
	}
}
