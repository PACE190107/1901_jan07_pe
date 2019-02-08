package com.revature.oop;

public class TestGifts extends Deadpool {
	public static void main(String[] args) {
		IronFist Danny = new IronFist();
		Danny.strength();
		Danny.weakness();
		new TestGifts().regenerate();
		new TestGifts().sarcasm();
	}

	@Override
	public void weakness() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strength() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quality() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void sarcasm() {
		System.out.println("Not everyone is sarcastic like Deadpool");
		
	}
}
