package com.revature.basics;

//import statements should be always after package
//package - declaration of class or compilation unit
//import java.lang.*; yes, you can do this however not 
//required for java.lang as they are pre-defined 
//(imported by default)

public class HelloWorld {

	// - single line comment
	//from java 1.5 main method is mandatory
	//before java 1.5 static block is more than 
	//enough and main method was not mandatory
	public static void main(String[] args) {
		System.out.println("hello world!!!");
		/* mutli line comments
		 * system - public final class 
			out - final and static variable of type 
		PrintStream
			println - method
		*/
		
		/**
		 *  java doc
		 */
	}
	
	public static void main() {
		System.out.println("overloaded main method");
	}
}
