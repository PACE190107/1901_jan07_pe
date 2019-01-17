package com.revature.collection;

public class GenericExample<T> {

	//Data type of these members will be whatever is specified during object instantiation
	private T myValue;
	
	public T getValue() {
		return myValue;
	}
	
	public void setvalue(T myValue) {
		this.myValue = myValue;
	}
	
	public static void main(String[] args) {
		
	}
}
