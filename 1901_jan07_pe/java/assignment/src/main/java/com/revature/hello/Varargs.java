package com.revature.hello;

public class Varargs {
	public static void main(String[] args) {
		new Varargs().display(10);
		new Varargs().display(10,20);
		new Varargs().display(10,20,30);
	}
	void display (int ...x) {
		System.out.println("=======");
		for (int i : x) {
			System.out.println(i);
		}
	}
}
