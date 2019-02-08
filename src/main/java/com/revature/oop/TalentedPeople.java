package com.revature.oop;

public interface TalentedPeople extends GiftedPeople {
	void weakness();
	default void demons() {
		System.out.println("Talented people have demons.");
	}
}
