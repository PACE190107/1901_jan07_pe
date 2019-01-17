package com.revature.week1tests;

public class ISBNTest {
	
	public boolean isValidIsbn(String string) {
		int validityCheck = 0;
		int checkDigit = 10;
		boolean containsIllegalCharacter = false;
		StringBuilder isbn = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				isbn.append(string.charAt(i));
			}
		}
		if (isbn.length() < 10) {
			if (string.charAt(string.length()-1) == 'X') {
				isbn.append('X');
			}
			else {
				boolean containsIllegalCharcter = true;
			}
		}
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isDigit(isbn.charAt(i))) {
				validityCheck = validityCheck + (Integer.parseInt(String.valueOf(isbn.charAt(i))) * checkDigit);
				checkDigit--;
			}else if (isbn.charAt(isbn.length()-1)=='X') {
				validityCheck += 10;
			}
		}
		validityCheck = validityCheck % 11;
		if (validityCheck == 0) {
			return true;
		}
		if (containsIllegalCharacter == true) {
			return false;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		ISBNTest isbn = new ISBNTest();
		isbn.isValidIsbn("3-598-21508-8");
		isbn.isValidIsbn("3-598-21507-X");
	}

}
