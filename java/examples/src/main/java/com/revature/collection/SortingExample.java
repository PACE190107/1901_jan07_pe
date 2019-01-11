package com.revature.collection;

import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {

	public static void main(String[] args) {
		sortUsingComparableAndComparator();
	}

	public static void sortUsingComparableAndComparator() {
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
		
		System.out.println("unsorted");
		System.out.println(Employee);
		System.out.println("======sorted using comparable ======");
		Collections.sort(Employee);
		System.out.println("sorted");
		System.out.println(Employee);
		System.out.println("======sorted using comparator ======");
		Collections.sort(Employee, Person.AgeComparator);
		System.out.println(Employee);		
	}
}
