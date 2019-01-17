package com.revature.week1tests;

public class PigLatin {
	public String toPigLatin(String string) {
		String[] splitPhrase = string.split(" ");
		String pigLatin = "";
		for (int i = 0; i < splitPhrase.length; i++) {
			if (i >= 1) {
				pigLatin = pigLatin.concat(" ");
			}
			char firstLetter = splitPhrase[i].charAt(0);
			System.out.println(firstLetter);
			while (Character.toString(firstLetter).matches("[aeiou]")==false) {
				if (Character.toString(firstLetter).matches("[q]")){
					String prefix = splitPhrase[i].substring(0, 2);
					String suffix = splitPhrase[i].substring(2);
					splitPhrase[i] = suffix.concat(prefix);
					firstLetter = splitPhrase[i].charAt(0);
				}
				else {
					String prefix = splitPhrase[i].substring(0, 1);
					String suffix = splitPhrase[i].substring(1);
					splitPhrase[i] = suffix.concat(prefix);
					firstLetter = splitPhrase[i].charAt(0);
				}
			}
			pigLatin = pigLatin.concat(splitPhrase[i].concat("ay"));
		}
		System.out.println(pigLatin);
		return pigLatin;
	}
	public static void main(String[] args) {
		PigLatin pigLatin = new PigLatin();
		pigLatin.toPigLatin("apple");
		pigLatin.toPigLatin("therapy");
		pigLatin.toPigLatin("school");
		pigLatin.toPigLatin("yellow");
		pigLatin.toPigLatin("quick fast run");
	}
}
