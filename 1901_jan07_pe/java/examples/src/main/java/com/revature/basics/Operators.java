package com.revature.basics;

import java.util.ArrayList;
import java.util.List;

// The import below does not work because of a naming collision (which List would the compiler use?)
//import java.awt.List;

public class Operators {
	
	public static void main(String[] args) {
		
		/*
		 * Operators
		 * 
		 * Types:
		 * 	- Unary
		 * 	- Arithmetic
		 * 	- Relational
		 * 	- Logical
		 * 	- Assignment
		 * 	- Bitwise Shifting
		 * 	- Ternary
		 */
		
		/*
		 * Unary Operators
		 * 
		 * 		Postfix and Prefix
		 */
		
		// prefix logical NOT
		boolean bool = true;
		System.out.println("not bool = " + !bool);
		
		// prefix positive
		int x = +456;
		System.out.println(x);
		
		// prefix negative
		int y = 78;
		System.out.println(-y);
		
		// prefix increment
		System.out.println(++x);
		
		// prefix decrement
		System.out.println(--x);
		
		// postfix increment
		System.out.println(y++);
		
		//postfix decrement
		System.out.println(y--);
		System.out.println(y);
		
		/*
		 * Arithmetic Operators
		 * 
		 * Addition
		 * Subtraction
		 * Multiplication
		 * Division
		 * Modulus
		 */
		// addition
		int mySum = 4 + 5;
		System.out.println(mySum);
		
		// subtraction
		int myDifference = mySum - 7;
		System.out.println(myDifference);
		
		// multiplication
		int myProduct = mySum * myDifference;
		System.out.println(myProduct);
		
		// division
		double myQuotient = myDifference / mySum;
		System.out.println(myQuotient); // this doesn't work properly, because our operands are ints! (The compiler is defaulting to integer division)
		
		myQuotient = 2 / 9;
		System.out.println(myQuotient); // this doesn't work
		
		myQuotient = 2d / 9d;
		System.out.println(myQuotient); // explicitly declaring the digits as double literals works!
		
		myQuotient = 2. / 9.; // digits are apparently optional after the decimal point
		System.out.println(myQuotient); // including the decimal places tells the compiler to treat these as floating point numbers
		
		myQuotient = 2. / 9;
		System.out.println(myQuotient); // we really only need to declare one of the operands as a double to get the compiler to do floating point division
		
		myQuotient = (double) myDifference / mySum;
		System.out.println(myQuotient); // to have compiler treat already declared variables differently, cast it as the type you need

		// REMEMBER TO BEWARE OF TRYING TO CONVERT LARGER DATA TYPES INTO SMALLER ONES!!!
		int myValue = 0;
		long myLongValue = 345345348768734345L;
		myValue = (int) myLongValue;
		System.out.println(myValue);
		
		// modulus operator (remainder)
		double myRemainder = 12 % 5;
		System.out.println(myRemainder);
		
		/*
		 * Relational Operators
		 */
		int a = 0;
		int b = 1;
		int c = 2;
		int d = 2;
		String myStr = "hello";
		
		ArrayList<String> myArrayList = new ArrayList<>(); 
		
		// Greater-than, Less-than, Greater-than-or-equal, Less-than-or-equal 
		System.out.println(a > b);
		System.out.println(a < b);
		System.out.println(c < d);
		System.out.println(c <= d);
		System.out.println(d >= a);
		System.out.println("------------"); // separator
		
		// instanceof returns true if the first operand is a type of the second operand
		System.out.println(myStr instanceof String);
		System.out.println(myStr instanceof Object); // since all Java objects directly/indirectly extend Object - this is true!
		System.out.println(myArrayList instanceof ArrayList);
		System.out.println(myArrayList instanceof List); // this returns true, even though List is an interface!
		System.out.println("------------"); // separator
		
		// is-equal, not-equal
		System.out.println(c == d);
		System.out.println(a != b);
		System.out.println("------------");
		
		java.awt.List myAwtList; 	// I can still access non-imported classes using the fully-qualified path name
		
		/*
		 * Logical Operators (Short-circuit operators)
		 * 
		 * For instance, with AND (&&) - we know that both operands have to be true in order for the result to be true. So, if 
		 * the first operand evaluated is false - then we already know that the result is going to be false.
		 * 
		 * Likewise, with OR (||) - the operation will return true if at least one of the operands is true. So, if the value of 
		 * the first operand is true, then there is no need to check the other.
		 */
		boolean value1 = true;
		boolean value2 = false;
		boolean value3 = true;
		
		// logical AND
		boolean result = value1 && value2;
		System.out.println(result);
		
		result = value1 && value3;
		System.out.println(result);
		
		// logical OR
		result = value1 || value2;
		System.out.println(result);
		
		/*
		 * Bitwise Operators (Short-Circuit Operators)
		 * 
		 * These are similar to the logical operators, and actually give the same results as their counterparts. But have specific 
		 * use cases dealing with binary values.
		 * 
		 * XOR is a bit different, it is also known as the Exclusive-OR. This operator returns true if exactly one operand
		 * has a true value. If both are true, then it will return false. Because of its nature, XOR must check both operands.
		 */
		// bitwise AND
		result = value2 & value1;
		System.out.println(result);
		
		// bitwise OR
		result = value1 | value2;
		System.out.println(result);
		
		// bitwise XOR
		result = value1 ^ value3;
		System.out.println(result);
		
		// declaring values in different bases
		int myBinary = 0b0010; // binary (base 2)
		int myOctal = 010; // octal (base 8)
		int myHex = 0x3f; // hexadecimal (base 16)
		
		System.out.println(myBinary);
		System.out.println(myOctal);
		System.out.println(myHex);
		
		/*
		 * Assignment Operators
		 * 
		 * In addition to the simple assignment that we have been using during variable initialization, we also have the compound
		 * assignment operators at our disposal. These include compound assignment operators for arithmetic and bitwise logical operators.
		 */
		// simple assignment
		int z = 54;
		
		// plus-equals
		z += 5;
		System.out.println(z);
		
		// minus-equals
		z -= 10;
		System.out.println(z);
		
		// multiply-equals
		z *= 4;
		System.out.println(z);
		
		// divide-equals
		z /= 2;
		System.out.println(z);
		
		// mod-equals
		z %= 6;
		System.out.println(z);
		
	}

}

