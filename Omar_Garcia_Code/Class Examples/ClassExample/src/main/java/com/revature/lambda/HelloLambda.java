package com.revature.lambda;

public class HelloLambda {
	
			//Provides implementation for functional interfaces
		public static void main(String[] args) {
			HelloFunctionalInterface english = new HelloFunctionalInterface(){
				 @Override
				 public void sayHello() {
					 System.out.println("Hello");
				 }
			};
			english.sayHello();
			
			HelloFunctionalInterface spanish = () -> System.out.println("Hola");
			HelloFunctionalInterface german = () -> System.out.println("Gutentag");
			HelloFunctionalInterface tamil = () -> System.out.println("Vanakkam");
			HelloFunctionalInterface french = () -> System.out.println("Bonjour");
			HelloFunctionalInterface italian = () -> System.out.println("ciao");
			
			spanish.sayHello();
			german.sayHello();
			tamil.sayHello();
			french.sayHello();
			italian.sayHello();
		}
}
