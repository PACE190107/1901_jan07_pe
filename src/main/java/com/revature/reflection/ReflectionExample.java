package com.revature.reflection;

public class ReflectionExample {
	
	public static void main(String[] args) {
		example();
	}
	
	static void example() {
		ReflectionExample re = new ReflectionExample();
		Runtime run = Runtime.getRuntime();
		
		System.out.println(run.freeMemory());
		System.out.println(run.totalMemory());
		System.out.println(run.maxMemory());
		System.out.println(run.availableProcessors());
		
		//approach1 - uses runtime class
		System.out.println(re.getClass().getName());
		System.out.println(re.getClass().getSuperclass());
		
		//approach2 - uses Class.forName
		try {
			Class<?> c = Class.forName("com.revature.reflection.ReflectionExample");
			System.out.println(c.getTypeName());
			System.out.println(c.getSuperclass());
			System.out.println(c.getName());
			System.out.println(c.isInterface());
			System.out.println(c.isEnum());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//approach3 - .class
	
	}
}
