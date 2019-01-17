package com.revature.patterns;

public class SingletonExample {
	
	private static SingletonExample instance;
	
	private SingletonExample() {
		System.out.println("private constructor");
	}
	
	public static SingletonExample getInstance() {
		if (instance == null ) {
			instance = new SingletonExample();
		}
		return instance;
	}
}
