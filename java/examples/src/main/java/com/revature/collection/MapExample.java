package com.revature.collection;

import java.util.HashMap;
import java.util.TreeMap;

public class MapExample {

	public static void main(String[] args) {
		treeMapExample();
		hashMapExample();
	}
	
	public static void treeMapExample(){
		TreeMap<Person, Person> emp1 = new TreeMap<Person, Person>();
		
		Person mike = new Person("Mike", 100, 19);
		Person rob = new Person("Rob", 82, 20);
		Person bob = new Person("Bob", 62, 24);
		Person kim = new Person("Kim", 73, 21);
		Person emma = new Person("Emma", 65, 18);
		
		emp1.put(mike, mike);
		emp1.put(rob, rob);
		emp1.put(bob, bob);
		emp1.put(kim, kim);
		emp1.put(emma, emma);
		
		System.out.println(emp1.entrySet());		
	}
	
	public static void hashMapExample() {
		HashMap<Person, Person> emp2 = new HashMap<Person, Person>();
		
		Person mike = new Person("Mike", 100, 19);
		Person rob = new Person("Rob", 82, 20);
		Person bob = new Person("Bob", 62, 24);
		Person kim = new Person("Kim", 73, 21);
		Person emma = new Person("Emma", 65, 18);
		
		emp2.put(mike, mike);
		emp2.put(rob, rob);
		emp2.put(bob, bob);
		emp2.put(kim, kim);
		emp2.put(emma, emma);
		
		System.out.println(emp2.entrySet());		
	}

}
