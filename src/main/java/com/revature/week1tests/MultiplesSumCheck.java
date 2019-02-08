package com.revature.week1tests;

import java.util.ArrayList;
import java.util.List;

public class MultiplesSumCheck {

	public int getSumOfMultiples(int i, int[] set) {
		List<String> multiples = new ArrayList<String>();
		int sum = 0;
		for (int input = 0; input < set.length; input++) {
			for (int checkIfFactor = 1; checkIfFactor < i; checkIfFactor++) {
				if (checkIfFactor % set[input] == 0) {
					String checkForDoubles = Integer.toString(checkIfFactor);
					if (multiples.contains(checkForDoubles)==false) {
						System.out.println("adding: " + checkIfFactor);
						multiples.add(Integer.toString(checkIfFactor));
					}
				}
			}	
		}
		for (int j = 0; j < multiples.size(); j++) {
			sum = sum + Integer.parseInt(multiples.get(j).toString());
		}
		System.out.println(sum);
		return sum;
	}
	
	public static void main(String[] args) {
		MultiplesSumCheck msc = new MultiplesSumCheck();
		int[] set = { 4, 6 };
		msc.getSumOfMultiples(15, set);
		
	}
}
