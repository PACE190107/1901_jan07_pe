package com.revature.week1tests;

public class CheckPrimes {
	
//	public int calculateNthPrime(int i) throws IllegalArgumentException {
//		if (i == 0) {
//			throw new IllegalArgumentException("Invalid number entered");
//		}
//		int nthPrime = 0;
//		int primeCount = 2;
//		int checkIfPrime = i;
//		while (primeCount <= i) {
//			boolean isPrime = false;
//			int j = 2;
//			do {
//				System.out.println(j);
//				if ((primeCount % j)==0) {
//					if (primeCount==j) {
//						System.out.println("is prime");
//						isPrime = true;
//					}
//				}
//				j++;
//			}  while (j <= i);
//			if (isPrime == true) {
//				nthPrime = primeCount;
//				checkIfPrime++;
//			}
//		} 
//		return nthPrime;
//	}
	public int calculateNthPrime(int i) throws IllegalArgumentException {
		if (i == 0) {
			throw new IllegalArgumentException("Invalid number entered");
		}
		int nthPrime = 0;
		int checkIfPrime = 2;
		for (int j = 1; j <= i; j++) {
			boolean isNotPrime = false;
			while (isNotPrime==false) {
				System.out.println("entering loop");
				for (int k = 2; k < checkIfPrime; k++) {
					if ((checkIfPrime % k)==0) {
						isNotPrime = true;
					}
				}
				if (isNotPrime==true) {
					checkIfPrime++;
					isNotPrime = false;
				} else if (isNotPrime==false) {
					nthPrime = checkIfPrime;
					checkIfPrime++;
					isNotPrime = true;
				} else if (checkIfPrime==2) {
					nthPrime = 2;
				}
			}
		}
	return nthPrime;	
	}
	
		


	public static void main(String[] args) {
		
		CheckPrimes check = new CheckPrimes();
		int i = check.calculateNthPrime(6);
		System.out.println(i);
	}

}
