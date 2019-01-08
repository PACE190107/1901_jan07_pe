package com.revature.hello;

public class DataTypes {

//int is primitive and Integer is an object
//Integer is the wrapper class of int
//meaning Integer is the object representation of int
	//primitive data types
	int i4 = 10;
	char c2;
	short s2;
	float f4;
	double d8;
	long l8;
	byte b1;
	boolean bo;
	
	//String is not primitve
	String s;
	Object o;
	
	public static void main(String[] args) {
	//can't call non-static method from static context
		//display();
		new DataTypes().displayDefaultValues();
		//if you don't have a constructor JVM 
		//will provide default constructor
	}
	
	void displayDefaultValues(){
		System.out.println("i : " + i4);
		System.out.println("c : " + c2);
		System.out.println("s : " + s2);
		System.out.println("f : " + f4);
		System.out.println("d : " + d8);
		System.out.println("l : " + l8);	
		System.out.println("bo : " + bo);
		System.out.println("b1 : " + b1);
		System.out.println("s : " + s);
		System.out.println("o : " + o);
	}	
}
