package com.revature.oop;

public class SunflowerBank {

	public static void main(String[] args) {
		Bank sunflower = new Bank();
		sunflower.setAccountName("Yuvi");
		sunflower.setAccountNumber(1234567890);
		sunflower.setAccountType(AccountType.Checking);
		sunflower.setRoutingNumber(1234567890);
		sunflower.setBalance(100);
		
		System.out.println(sunflower.getAccountName() + 
				"\t" + sunflower.getBalance()
				);
	}
}
