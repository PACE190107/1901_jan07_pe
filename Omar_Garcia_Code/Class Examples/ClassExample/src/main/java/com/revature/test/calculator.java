package com.revature.test;

public class calculator implements calci{
	
	@Override
	public int add(int x, int y) {
		return (x+y);
	}

	@Override
	public int sub(int x, int y) {
		return (x-y);
	}
	
	@Override
	public int divide(int x, int y) {
		return (x/y);
	}
	
	@Override
	public int multiply(int x, int y) {
		return (x*y);
	}
}
