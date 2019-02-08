package com.revature.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionExample {
	public static void main(String[] args) {
		//arrayListExample();
		setExample();
		
	}
	
	private static void arrayListExample() {
		List<String> superHeroes = new ArrayList<String>();
		superHeroes.add("Spider-Man");
		superHeroes.add("Bat Man");
		superHeroes.add("Super Man");
		superHeroes.add("Wonder Woman");
		superHeroes.add("Hulk");
		superHeroes.add("Thor");
		
		System.out.println("size: " + superHeroes.size());
		System.out.println("get value: " + superHeroes.get(3));
		System.out.println("remove: " + superHeroes.remove(3));
		superHeroes.add("Iron Man");
		superHeroes.add("Dr. Strange");
		System.out.println("size: " + superHeroes.size());
		System.out.println("=============");
		//easiest way to traverse list
		for(String s: superHeroes) {
			System.out.println(s);
		}
		
		System.out.println(superHeroes.toString());
		
	}
	
	private static void setExample() {
		Set<String> moreHeroes = new HashSet<String>();
		moreHeroes.add("Daredevil");
		moreHeroes.add("Wolverine");
		moreHeroes.add("Flash");
		moreHeroes.add("Black Panther");
		moreHeroes.add("Aquaman");
		System.out.println(moreHeroes);
	}
	
	
}
