package com.revature.basics;

public class Scopes {
	
	// 1 - Static variables
	// 2 - Static blocks
	// 3 - Non Static Variables
	// 4 - Non Static Block
	// 5 - Constructor

	static int i = 10;		//class scope
	public int j = 20;
	int k = 30;
	{
		System.out.println("non-static block ");
		System.out.println(++i);
		System.out.println(++j);
		int m = 30;		//scope of m is limited for this block
	}
	public Scopes() {
		System.out.println("constructor");
		System.out.println(++i);
		System.out.println(++j);
	}
	static {
		System.out.println("static block");
		System.out.println(++i);
		//System.out.println(++j);	
		//can't access non-static member in a static context
		//can I access static member from non-static context? - yes
	}
	public static void main(String[] args) {
		int n = 40;
		System.out.println("main method");
		System.out.println(++i);
		new Scopes();
		System.out.println(++i);
		System.out.println(new Scopes().j);
	}
	
	void myMethod(){
		returnInt();
		System.out.println(new Scopes().j);
		//System.out.println(i);
	}

	static int returnInt() {
		return 1;
	}
}

