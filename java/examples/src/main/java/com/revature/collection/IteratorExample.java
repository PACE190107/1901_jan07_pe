package com.revature.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {

	public static void main(String[] args) {
		iteratorMethod();
	}

	public static void iteratorMethod() {
		ArrayList<Person> Employee = new ArrayList<>();
		Person mike = new Person("Mike", 100, 19);
		Person rob = new Person("Rob", 82, 20);
		Person bob = new Person("Bob", 62, 24);
		Person kim = new Person("Kim", 73, 21);
		Person emma = new Person("Emma", 65, 18);
		
		Employee.add(mike);
		Employee.add(rob);
		Employee.add(bob);
		Employee.add(kim);
		Employee.add(emma);
		
		Iterator<Person> iter1 = Employee.iterator();
		System.out.println("======using while======");
		while(iter1.hasNext()) {
			Person p = iter1.next();
			System.out.println(p.name);
		}
		System.out.println("======using enhanced for loop======");
		for(Person p: Employee) {
			System.out.println(p.name);
		}		
	}
}