package com.revature.oop;

public class TestGifts extends DeadPool{

	public static void main(String[] args) {
		IronFist Danny = new IronFist();
		Danny.strength();
		Danny.weakness();
		
		regenerate();
		new TestGifts().sarcasm();
	}

	@Override
	public void weakness() {
		
	}

	@Override
	public void strength() {
		
	}

	@Override
	public void speed() {
		
	}

	@Override
	public void quality() {
		
	}

	@Override
	void sarcasm() {
		System.out.println("Not everyone is sarcastic like dead pool");
	}

}
