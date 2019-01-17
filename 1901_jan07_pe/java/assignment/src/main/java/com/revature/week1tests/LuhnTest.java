package com.revature.week1tests;

public class LuhnTest {

	public boolean isLuhnValid(String string) {
		System.out.println(string);
		String stringOnlyNumbers = string.replaceAll("[^0-9]", "");
		System.out.println(stringOnlyNumbers);
		StringBuffer sb = new StringBuffer();
		String stringReversed = sb.append(stringOnlyNumbers).reverse().toString();
		System.out.println(stringReversed);
		int luhn = 0;
		int currentDigit = 0;
		boolean luhnCheck = false;
		for (int i = 0; i < stringReversed.toString().length(); i++) {
			if ((i % 2 == 0)&&(i != 0)) {
				currentDigit = (Integer.parseInt(String.valueOf(stringReversed.charAt(i)))) * 2;
				if (currentDigit > 9) {
					currentDigit-=9;
					luhn = luhn + currentDigit;
				}
			} else {
				currentDigit = Integer.parseInt(String.valueOf(stringReversed.charAt(i)));
				luhn = luhn + currentDigit;
			}
		}
		if (luhn % 10 == 0) {
			luhnCheck = true;
		} 
		return luhnCheck;
	}

	public static void main(String[] args) {
		LuhnTest luhn = new LuhnTest();
		luhn.isLuhnValid("046 454 286");
	}

}

