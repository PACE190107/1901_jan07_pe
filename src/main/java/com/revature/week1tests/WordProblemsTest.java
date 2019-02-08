package com.revature.week1tests;

import java.util.ArrayList;
import java.util.List;

public class WordProblemsTest {

public int solveWordProblem(String string) {
	List<String> numbers = new ArrayList<String>();
	int finalAnswer = 0;
	StringBuffer numberBuilder = new StringBuffer();
	for (int i = 0; i < string.length(); i++) {
		numberBuilder.delete(0, numberBuilder.capacity());
		if ((Character.isDigit(string.charAt(i))==true)) {
			numberBuilder = numberBuilder.append(string.charAt(i));
			while (Character.isDigit(string.charAt(i+1))==true) {
				i++;
				numberBuilder = numberBuilder.append(string.charAt(i)); 
				System.out.println(numberBuilder.toString() + "adding");
			}
		numbers.add(numberBuilder.toString());	
		}
	}
	System.out.println(numbers.get(0));
	System.out.println(numbers.get(1));
	if (string.contains("plus")==true) {
		finalAnswer = Integer.parseInt(numbers.get(0).toString()) + Integer.parseInt(numbers.get(1).toString());
		System.out.println(finalAnswer);
	}
	else if (string.contains("minus")==true) {
		finalAnswer = Integer.parseInt(numbers.get(0).toString()) - Integer.parseInt(numbers.get(1).toString());
	}
	else if (string.contains("multipl")==true) {
		finalAnswer = Integer.parseInt(numbers.get(0).toString()) * Integer.parseInt(numbers.get(1).toString());
	}
	else if (string.contains("divid")==true) {
		finalAnswer = Integer.parseInt(numbers.get(0).toString()) / Integer.parseInt(numbers.get(1).toString());
	}
	return finalAnswer;
}

	public static void main(String[] args) {
		WordProblemsTest wpt = new WordProblemsTest();
		wpt.solveWordProblem("What is 1 plus 1?");
	}

}
