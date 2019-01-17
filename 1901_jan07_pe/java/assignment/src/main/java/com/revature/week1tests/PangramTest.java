package com.revature.week1tests;

public class PangramTest {
	
	public boolean isPangram(String string) {
		StringBuilder pangramCheck = new StringBuilder();
		pangramCheck.append("abcdefghijklmnopqrstuvwxyz");
		char charCheck;
		int indexCheck;
		string = string.toLowerCase();
		System.out.println(string);
		StringBuilder noSpaces = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetter(string.charAt(i))) {
				noSpaces.append(string.charAt(i));
			}
		}
		System.out.println(noSpaces);
		for (int i = 0; i < noSpaces.length(); i++) {
			charCheck = noSpaces.charAt(i);
			for (int j = 0; j < pangramCheck.length(); j++) {
				if (pangramCheck.charAt(j)==charCheck) {
					pangramCheck.deleteCharAt(j);
				}
			}
		}
		if (pangramCheck.toString().isEmpty()) {
			System.out.println(true);
			return true;
		} else {
			System.out.println(false);
			return false;
		}
	}


	public static void main(String[] args) {
		PangramTest pangram = new PangramTest();
		pangram.isPangram("");
		System.out.println("============");
		pangram.isPangram("abcdefghijklmnopqrstuvwxyz");
		System.out.println("============");
		pangram.isPangram("the quick brown fox jumps over the lazy dog");
		System.out.println("============");
		pangram.isPangram("a quick movement of the enemy will jeopardize five gunboats");
		System.out.println("============");
		pangram.isPangram("five boxing wizards jump quickly at it");
		System.out.println("============");
	}

}
