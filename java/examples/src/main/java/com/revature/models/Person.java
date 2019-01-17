package com.revature.models;
/*
 * This is a class. A class is a blueprint, or a template, from which objects are created.
 * Classes can contain variables, methods, and nested classes - which are all collectively
 * known as the 'members' of the class.
 * 
 * If no parent class is explicitly extended, then the Object class is implicitly extended.
 */
public class Person {

	/*
	 * Instance variables are unique values specific to each class. The manipulation of these
	 * fields in one object will not cause the same fields within a different instance of the
	 * object to change.
	 */
	public String firstName = "John";
	public String lastName = "Sample";
	public int age_yrs = 35;
	public double height_in = 72.0;
	public double weight_lbs = 180;
	
	/*
	 * These are class variables. Class variables are different from instance variables in that
	 * they belong to the class itself, and not to any specific instance of the class.
	 * 
	 * Class members, whether they are variables or methods, can be accessed by simply invoking
	 * the class name and using the dot operator to access the desired class member.
	 * 
	 * The value of class variables will be the same across all instances of the class. If we use
	 * one initialized object to change the static variable, it will change it for all other instances
	 * of that object.
	 */
	public static final boolean IS_HUMAN = true;
	public static int someValue;
	
	/*
	 * Constructors
	 * 
	 * A constructor is a block of code, similar to a method, that is called when an instance of an object is created.
	 * Constructors do not have return types, and the name of the constructor must match the name of the class.
	 * 
	 * Constructors do not have to have a specific access modifier, although typically they are declared as public. However,
	 * there are specific instances in which a non-public constructor would be desired (i.e. singleton design pattern).
	 */
	
	/*
	 *  This is a no-args constructor, literally meaning that it takes no arguments. Usually we would not need
	 *  to include this constructor, because Java provides a default constructor if we do not have any parameterized
	 *  constructors.
	 */
	public Person() {
		
	}
	
	/*
	 * This is a parameterized constructor, also known as an overloaded constructor.
	 */
	public Person(String fn) {
		this.firstName = fn;
	}
	
	/*
	 * This is a method, a method is a set of Java commands referred to by name. You are able to execute all the 
	 * commands by using the name of the method. Methods can take values as parameters and can, optionally, return 
	 * a value as a result.
	 */
	public double calculateBMI() {
		return (this.weight_lbs / this.height_in / this.height_in) * 703;
	}
	
	/*
	 * Methods can be overloaded. This means that we can have two methods with the same method name, but each takes a 
	 * different set of parameters (the order of these parameters is important if you have multiple types)
	 */
	public double calculateBMI(double height, double weight) {
		return (weight / height / height) * 703;
	}
	
	/*
	 * This is a static method, which means that this method belongs to the class and not to a specific instance. This
	 * method can be accessed in the same way as other class members.
	 */
	public static void eat() {
		System.out.println("The person is eating.");
	}


}
