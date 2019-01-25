package com.revature.oop;
//Has a, aggregation
public class SunFlowerBank {
	public static void main(String[] args) {
	Bank Sunflower = new Bank();
	Sunflower.setAccountName("YUVI");
	Sunflower.setAccountType(AccountType.checking);
	Sunflower.setAccountNumber(1234567890);
	Sunflower.setRoutingNumber(1234567890);
	Sunflower.setEmail("example@website.com");
	Sunflower.setBalance(100);
	
	System.out.println(Sunflower.getAccountName() + "/t" + Sunflower.getBalance());
	}
}
