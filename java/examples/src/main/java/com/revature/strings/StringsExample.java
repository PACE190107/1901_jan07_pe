package com.revature.strings;

import java.util.StringTokenizer;

public class StringsExample {

	String s1 = "hello";
	String s2 = "world";
	//Strings are immutable
	//immutable means can not be changed
	
	public void printVariables() {
//accessing instance variables from non-static method
		System.out.println("print" + s1 + s2);	//printhelloworld
		System.out.println(s1 + s2 + 1);		//hw1
		System.out.println(1 + s1 + s2 + 1 + 2);//1hw12 1hw3
		System.out.println(1 + 23 + s1 + s2 + 1 + 34);//24hw134
	}
	
	public void comparison() {
//==equality operator
//compare the references of objects and not the value
//equals method compares the value
		
		String s3 = "Hardwork beats talent when talent is not working hard - William Gentry";
		String s4 = "Hardwork beats talent when talent is not working hard - William Gentry";
		
		//Can two objects be equal and have the same hashcode? - yes
		System.out.println(s3 == s4);		//true
		System.out.println(s3.equals(s4));	//true
		
		String s1 = "Hardwork beats talent when talent is not working hard - really?";
		//can two object be not equal and have same hashcode? - no
		System.out.println(s3 == s1);		//false
		System.out.println(s3.equals(s1));	//false
		
		//can two objects be equal and have different hashcode? - yes
		String s5 = new String("Hardwork beats talent when talent is not working hard - William Gentry");
		System.out.println(s5 == s4);		//false
		System.out.println(s5.equals(s4));	//true
		
		//can two objects be not equal and have different hashcode? - yes
		String s6 = "Always expect the unexpected";
		String s7 = "Defenders is not the popular marvel";
		System.out.println(s6 == s7);		//false
		System.out.println(" " + s6.equals(s7) + " hashcode: "+ s6.hashCode() + ", " + s7.hashCode());	//false
		
	}
	
	void letsDoMoreFunWithStrings(){
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		System.out.println(s7.length());
		System.out.println(s7.charAt(10));	//11th character
		System.out.println(s7.indexOf("and"));
		System.out.println("======");
		System.out.println(s7.substring(20)); 
		System.out.println(s7.indexOf("ing"));
		System.out.println(s7.replace("ing", "ABCD"));
		System.out.println(s7.toString());
		for (String s: s7.split("ing")) {
			System.out.println(s);
		}
	}
	
	void stringTokenizer(){
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		StringTokenizer st = new StringTokenizer(s7, "ing");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
	
	public static void main(String[] args) {
		StringsExample se1 = new StringsExample();
		//se1.printVariables();
		//se1.comparison();
		//se1.letsDoMoreFunWithStrings();
		//se1.stringTokenizer();
		se1.performanceCheck();
	}
	
	void performanceCheck(){
		String s1 = "";
		long startTime1 = System.nanoTime();
		for (int i=0; i< 1000; i++) {
			s1 = s1.concat("a");
		}
		long endTime1 = System.nanoTime();
		System.out.println("String " + (startTime1 - endTime1));
		
		StringBuilder s2 = new StringBuilder();
		long startTime2 = System.nanoTime();
		for (int i=0; i< 1000; i++) {
			s2 = s2.append("a");
		}
		long endTime2 = System.nanoTime();
		System.out.println("String Builder " + (startTime2 - endTime2));
		
		StringBuffer s3 = new StringBuffer();
		long startTime3 = System.nanoTime();
		for (int i=0; i< 1000; i++) {
			s2 = s2.append("a");
		}
		long endTime3 = System.nanoTime();
		System.out.println("String Buffer " + (startTime3 - endTime3));		
	}

}
