package com.revature.basics;

import com.revature.models.Person;

public class Employee {

	public static void main(String[] args) {
		// Create a Person object using the no-args constructor (object instantiation)
		Person myPerson = new Person();
		// print out the first name of our Person object by invoking the public field
		// 'firstName'
		System.out.println(myPerson.firstName);
		// print out the calculated BMI of our Person object by simply invoking the
		// method name
		System.out.println(myPerson.calculateBMI());
		// We can also invoke static methods without having to directly instantiate a
		// Person object.
		Person.eat();
	}
}
