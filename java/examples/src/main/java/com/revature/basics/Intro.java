package com.revature.basics;

/*
 * A package is a namespace that organizes a set of related classes and 
 * interfaces. Conceptually you can think of packages as being similar 
 * to different folders on your computer.
 */

public class Intro {

	/*
	 * Primitive data types
	 * 
	 * The Java programming language is statically-typed, which means that 
	 * all variables must first be declared before they can be used. This 
	 * involves stating the variable's type and name.
	 * 
	 * Types:
	 * 		byte, short, int, long, float, double, char, boolean
	 */

	/*
	 * You can place underscores only between digits; you cannot place 
	 * underscores in the following places:
	 * 
	 * 		+ At the beginning or end of a number
	 * 		+ Adjacent to a decimal point in a floating point literal
	 * 		+ Prior to an F or L suffix
	 * 		+ In positions where a string of digits is expected
	 */

	/*
	 * Literal suffixes
	 * 
	 * For the long, float, and double primitive types, literal suffixes 
	 * should be appended to these in order to distinguish them from int
	 * literals.
	 */

	byte myByte = 127;
	short myShort = 32_767;
	int myInt = 76_543_678;
	long myLong = 3453456L;

	float myFloat = 3.14159f;
	double myDouble = 45.2342342d;

	char myChar = 'a';

	boolean myBoolean = true;

	public static void main(String[] args) {

		// local variable declaration
		byte byteValue;

		// variable initialization
		byteValue = 8;

		// local variable declaration and initialization
		int myOtherInt = 123_456_789;

		/*
		 * System is a class, like String, that is provided within the java.lang package -
		 * so we do not have to import it to use it. The System class contains several useful
		 * static methods (see Person.java for insight on static methods) that can be invoked
		 * for our uses. Here we are using the 'out' class variable (which is of type PrintStream),
		 * and then invoking the println and print commands on that object to print text to the
		 * console.
		 */
		System.out.println("Hello, World!");
		System.out.print("this is another statement");
		System.out.print(" this is on the same line\n");
		System.out.println(myOtherInt);

		/*
		 * Wrapper classes
		 * 
		 * For every primitive type in Java, there is a built-in object type 
		 * called a wrapper class. For example, the wrapper class for 'int' 
		 * is called Integer; for 'double' it is called Double. Wrapper classes 
		 * are useful for several reasons:
		 * 
		 * 	+ Each wrapper class contains special values (like the minimum and 
		 * 	  maximum values for the type) and methods that are useful for 
		 * 	  converting between types. 
		 * 
		 * 	+ You can instantiate wrapper classes and create objects that contain 
		 * 	  primitive values. In other words, you can wrap a primitive value up 
		 * 	  in an object, which is useful if you want to invoke a method that 
		 * 	  requires an object type.
		 * 
		 */

		System.out.println("byte, minimum value: " + Byte.MIN_VALUE);
		System.out.println("byte, maximum value: " + Byte.MAX_VALUE);

		System.out.println("char, maximum value: " + Character.MAX_VALUE);

		System.out.println("boolean, logicalXor(true, true): " + Boolean.logicalXor(true,  true));

		/*
		 * Autoboxing is the automatic conversion that the Java compiler makes between the primitive 
		 * types and their corresponding object wrapper classes.
		 */
		Character autoboxedChar = 'a';
		System.out.println(autoboxedChar);

		/* 
		 * If the conversion goes the other way, this is called unboxing. For example:
		 */
		Integer[] myIntegerList = new Integer [4];
		for(int i = 0; i < 4; i++) {
			myIntegerList[i] = i; // 'i' is declared as an int, but is being stored in myIntegerList as an Integer!
		}
	}

}