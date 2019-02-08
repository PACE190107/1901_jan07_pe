package com.revature.strings;

import java.util.StringTokenizer;

public class StringExample {
	
	String s1 = "hello";
	String s2 = "world";
	//Strings are immutable
	//immutable means cannot be changed
	
	public void printVariables() {
		//accessing instance variables from non-static method
		System.out.println("print " + s1 + s2);  //print helloworld
		System.out.println(s1 + s2 + 1);		 //helloworld1
		System.out.println(1 + s1 + s2 + 1 + 2); //1helloworld12
		System.out.println(1 + 23 + s1 + s2 + 1 + 34);  //24helloworld134
		//goes from left to write, starting with math operation
		//switches to string operation and concatenates
	}
	
	public void  comparison() {
		//==equality operator
		//compares the references of objects and not the value itself
		//equals method compares the value
		String s3 = "Hardwork beats talent when talent is not working hard. - William Gentry";
		String s4 = "Hardwork beats talent when talent is not working hard. - William Gentry";
		//Can two objects be equal and have the same hashcode?-yes
		System.out.println(s3 == s4);//true
		System.out.println(s3.equals(s4));//true
		//Can two objects be not equal and have the same hashcode? -no
		String s1 = "Hardwork beats talent when talent is not working hard. - really?";//new string object is created-new hash
		System.out.println(s3 == s1);//false
		System.out.println(s3.equals(s1));//false
		//can two objects be equal and have different hashcode? - yes
		String s5 = new String("Hardwork beats talent when talent is not working hard. - William Gentry");//same string, new object created
																										//created in heap, not in String pool
		System.out.println(s3 == s5);//false - different hashcodes
		System.out.println(s3.equals(s5));//true - same value
		//can two objects be not equal and have different hashcode?  -yes
		String s6 = "Always expect the unexpected";
		String s7 = "Defenders is not the popular Marvel";
		System.out.println(s6 == s7);//false-diff hashcodes
		System.out.println(s6.equals(s7) + "hashcode: " + s6.hashCode() + ", " + s7.hashCode()); //false - diff values
	}
	
	void letsDoMoreFunWithStrings(){
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		System.out.println(s7.length());
		System.out.println(s7.charAt(10));//index starts at 0
		System.out.println(s7.indexOf("and"));//location of substring starting w/ first char
		System.out.println(s7.substring(20));
		System.out.println(s7.indexOf("ing"));
		System.out.println(s7.replace("ing", "ABCD"));
		System.out.println(s7.toString());//return object
		for(String s: s7.split("ing")) {
			System.out.println(s);
		}
	}
	
	void stringTokenizer() {
		//removes characters i, n, and g
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		StringTokenizer st = new StringTokenizer(s7, "ing");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
	
	public static void main(String[] args) {
		StringExample se1 = new StringExample();
		//se1.printVariables();
		//se1.comparison();
		//se1.letsDoMoreFunWithStrings();
		se1.stringTokenizer();
	}
}
