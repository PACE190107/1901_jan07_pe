package com.revature.hello;


//private constructor w/ getter allows for only 1 instance of object to be created
public class SingletonExample {
	
	private static SingletonExample instance;
	
	private SingletonExample() {
		System.out.println("private constructor");
	}
	
	public static SingletonExample getInstance() {
		if (instance == null) {
			instance = new SingletonExample();
		}
		return instance;
	}
}
