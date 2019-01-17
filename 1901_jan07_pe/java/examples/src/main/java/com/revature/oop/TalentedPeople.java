package com.revature.oop;

public interface TalentedPeople extends GiftedPeople{
	
	void weakness();
	default void daemons() {
		System.out.println("Talented People have daemons");
	}
	
}
