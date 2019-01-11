package com.revature.basics;

public class Varargs {

	public static void main(String[] args) {
		new Varargs().display(10);
		new Varargs().display(10,20);
		new Varargs().display(10,20,30);
		new Varargs().display(10,20,30,40,50,60);
	}
	
	void display(int ...x){
		System.out.println(x[0]);
		//same way to access arrays works for varargs
		System.out.println("===========");
		for (int a : x) {
			System.out.println(a);
		}
	}

}
