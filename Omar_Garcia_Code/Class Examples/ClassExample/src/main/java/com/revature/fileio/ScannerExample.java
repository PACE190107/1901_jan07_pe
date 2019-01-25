package com.revature.fileio;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String[] args) {
		new ScannerExample().scanInputs();
	}

	private void scanInputs() {
		System.out.print("Enter your name: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		System.out.print("Enter your age: ");
		int age = sc.nextInt();
		System.out.println(age);
		sc.close();
	}

}
