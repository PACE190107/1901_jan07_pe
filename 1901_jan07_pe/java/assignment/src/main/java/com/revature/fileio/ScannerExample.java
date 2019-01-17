package com.revature.fileio;

import java.util.Scanner;

public class ScannerExample {
	
	
	public static void main(String[] args) {
		new ScannerExample().scanInputs();
	}

	private void scanInputs() {	
		System.out.println("Enter your name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.println("Enter your age: ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("name " + name + ", age " + age);
		sc.close();
	}
}
