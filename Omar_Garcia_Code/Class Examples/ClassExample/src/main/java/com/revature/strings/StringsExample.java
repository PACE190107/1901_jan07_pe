package com.revature.strings;
import java.util.*;

public class StringsExample {
	String s1 = "hello";
	String s2 = "World";
	//Strings are immutable
	//immutable means can not be changed
	
	public void printVariable() {
		//accessing instance variables from non-static method
		System.out.println("print " + s1 + s2);
		System.out.println(s1 + s2 + 1);
		System.out.println(1 + s1 + s2 + 2);
		System.out.println(1 + s1 + s2 + 2 + 3);
		System.out.println(1 + 2 + s1 + s2 + 34 + 56);
	}
	
	public void comparison() {
		//==equality operator
		//compare the references of objects and not the value
		//equals method compares the value
		String s3 = "Hardwork beats talent when talent doesn't work hard";
		String s4 = "Hardwork beats talent when talent doesn't work hard";
		
		//can two objects be equal and have the same hash code? - yes
		System.out.println(s3 == s4); //true
		System.out.println(s3.equals(s4)); //true
		
		//can two objects be not equal and have the same hash code?
		//no, it's impossible
		
		//Can two objects be equal and have different hash codes - yes
		String s5 = new String(s3);
		String s6 = new String(s3);
		System.out.println(s5 == s6);    //false
		System.out.println(s5.equals(s6));  //true
		
		//can two objects be not equal and have two different hash codes
		String s1 = "Hardwork beats talent when talent doesn't work weak";
		
		System.out.println(s3 == s1);    //false
		System.out.println(s3.equals(s1));  //false
		
		System.out.println("hashcode: " + s3.hashCode() + " " + s4.hashCode());
	}
	
	public void letsDoMoreFunWithStrings() {
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		System.out.println(s7.length());
		System.out.println(s7.charAt(10));
		System.out.println(s7.indexOf("and"));
		System.out.println(s7.substring(20)); //Creates new string with the first int characters removed
		System.out.println(s7.indexOf("ing"));
		System.out.println(s7.replace("ing", "ABCD"));
		System.out.println(s7.toString());
		for (String s: s7.split("ing"))
		{
			System.out.println(s);
		}
	}
	
	public void StringTokenizer() {
		String s7 = "I like playing Pokemon while sleeping, eating, driving, walking, and everywhere else";
		StringTokenizer st = new StringTokenizer(s7,"ing");
		
	}
	
	public static void main(String[] args) {
		StringsExample se1 = new StringsExample(); 
		//se1.printVariable();
		//se1.comparison();
		se1.letsDoMoreFunWithStrings();
	}
}
