package com.revature.collection;

public class GenericExample <T> {
	private T myValue;
	
	public T getValue() {
		return myValue;
	}
	
	public void setValue(T myValue) {
		this.myValue = myValue;
	}
}
