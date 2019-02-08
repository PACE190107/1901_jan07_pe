package com.revature.hello;

public class DataTypes {
	
	//# = number of bytes used  in primitives
	int i4;
	char c2;
	short s2;
	float f4;
	double d8;
	long l8;
	byte b1;
	boolean b0;
	
	//String is not a primitive
	String s;
	Object o;
	
	void displayDefaultValues() {
		System.out.println("i : " + i4);
		System.out.println("c : " + c2);
		System.out.println("s : " + s2);
		System.out.println("f : " + f4);
		System.out.println("d : " + d8);
		System.out.println("l : " + l8);
		System.out.println("by : " + b1);
		System.out.println("bo : " + b0);
		System.out.println("s : " + s);
		System.out.println("o : " + o);
	}
	
	public static void main(String[] args) {
		new DataTypes().displayDefaultValues();
	}
}
