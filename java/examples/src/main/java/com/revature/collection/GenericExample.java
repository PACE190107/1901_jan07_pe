package com.revature.collection;

public class GenericExample<T> {

	//Data type of these members will be whatever is specified during object instantiation
	private T myValue;
	
	public T getValue() {
		return myValue;
	}
	
	public void setValue(T myValue) {
		this.myValue = myValue;
	}
}
