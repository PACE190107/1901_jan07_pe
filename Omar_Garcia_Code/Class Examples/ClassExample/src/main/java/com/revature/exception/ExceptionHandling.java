package com.revature.exception;

public class ExceptionHandling {
	public static void main(String[] args) {
//		System.out.println(divideByZero(10, 20));
//		System.out.println(divideByZero(10, 0));
//		createNullPointerException();
		try {
		createNumberFormatException();
		} finally {
			System.out.println("Everthing is cool");
		}
	}
	
	public static int divideByZero(int x, int y) {
		int z = 0;
		int i[] = {14,25,63,74,58};
		
		for(int j: i) {
			System.out.println(j);
		}
		
//		System.out.println(i[0] + ": 0");
//		System.out.println(i[4] + ": 4");
//		System.out.println(i[5] + ": 5");
//		System.out.println(i[6] + ": 6");
		
		try {
		z = x / y;
		} catch (ArithmeticException e){
			//e.getMessage();
			//e.printStackTrace();
			//e.getStackTrace();
			System.out.println("Something unexcpeted happened");
		}
		return z;
	}
	
	public static void createNullPointerException() {
		String s = null;
		try {
			System.out.println(s.indexOf(1));
		}catch(NullPointerException | StringIndexOutOfBoundsException np) {
		
			System.out.println("I was't expecting this");
		}finally {
			System.out.println("This line will always be excecute");
		}
	}
	
	public static void createNumberFormatException() throws NumberFormatException {
		String s1 = "early bird catches the worm";
		int i = 10;
		int j = 20;
		i = 30;
		i = Integer.parseInt(s1);
		//wrapping // Boxing
	}
}
