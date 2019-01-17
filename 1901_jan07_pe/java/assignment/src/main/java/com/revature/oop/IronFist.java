package com.revature.oop;

public class IronFist implements TalentedPeople {

	@Override
	public void strength() {
		System.out.println("Danny's strength is his glowing fist");		
	}

	@Override
	public void speed() {
		System.out.println("He lost to Davos few times");
	}

	@Override
	public void quality() {
		System.out.println("Danny is rich and virtuous");
	}

	@Override
	public void weakness() {
		System.out.println("Danny is naive");
	}

	@Override
	public void demons() {
		System.out.println("Dragon got killed");
	}

}
