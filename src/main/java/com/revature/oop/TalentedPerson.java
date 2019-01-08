package com.revature.oop;

public interface TalentedPerson extends GiftedPerson {
	void weakness();
	default void multiDimensional() {
		System.out.println("Talented people have demons.");
	}
}
