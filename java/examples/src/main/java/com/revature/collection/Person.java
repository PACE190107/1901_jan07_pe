package com.revature.collection;

import java.util.Comparator;

public class Person implements Comparable<Person> {
	String name;
	int height;
	int age;
	
	public Person() {
		super();
	}
	public Person(String name, int height, int age) {
		super();
		this.name = name;
		this.height = height;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", height=" + height + ", age=" + age + "]";
	}
	//Comparable has to be implemented & compareTo has to be overridden
	//Comparable has a single argument
	@Override  
	public int compareTo(Person p1) {
		if(this.height < p1.height) {
			return -1;
		} else if (this.height > p1.height) {
			return 1;
		} else
		return 0;
	}
	//Comparator does not have to be implemented but can be implemented and compare must be overridden
	//Comparator takes 2 arguments
	public static Comparator<Person> AgeComparator = new Comparator<Person> () {

		@Override
		public int compare(Person p1, Person p2) {
			if (p1.age < p2.age) 
				return -1;
			else if(p1.age > p2.age)
				return 1;
			return 0;
		}
		
	};
}
