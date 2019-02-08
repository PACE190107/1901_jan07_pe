package com.revature.test;

public class Calculator implements Calci {
	
	public int add(int x, int y){
		return x + y;
	}
	
	public int sub(int x, int y){
		return x - y;
	}
	
	public int divide(int x, int y){
		return x / y;
	}
	
	public int multiply(int x, int y){
		return x * y;
	}
}
