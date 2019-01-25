package com.revature.exception;

public class Main {

	public static void main(String[] args) {
		try {
		new Main().riskyBehaviour();
		} catch(HankException e){
			
		}
	}
	
	public void riskyBehaviour() throws HankException{
		System.out.println("Before Exception");
		throw new HankException();
		//Anything after throws will not be executed
	}
}
