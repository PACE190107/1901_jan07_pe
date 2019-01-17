package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * DONE
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * DONE
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String acronym = null;
		for (int i = 0; i < phrase.length(); i++) {
			if (i == 0)
				acronym = String.valueOf(phrase.charAt(i));
			else if (Character.isLetterOrDigit(phrase.charAt(i-1))==false) {
				if (Character.isSpaceChar(phrase.charAt(i))==false)	
					acronym = acronym.concat(String.valueOf(phrase.charAt(i)));
			}
		}
		acronym = acronym.toUpperCase();
		return acronym;
	}

	/**
	 * DONE
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */

	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if ((this.sideOne==this.sideTwo)&&(this.sideOne==this.sideThree)&&(this.sideTwo==this.sideThree))
				return true;
			else
				return false;
		}

		public boolean isIsosceles() {
			if ((this.sideOne==this.sideTwo)||(this.sideOne==this.sideThree)||(this.sideTwo==this.sideThree))
				return true;
			else
				return false;
		}

		public boolean isScalene() {
			if ((this.sideOne!=this.sideTwo)&&(this.sideOne!=this.sideThree)&&(this.sideTwo!=this.sideThree))
				return true;
			else
				return false;
		}

	}

	/**
	 * DONE
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int score = 0;
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			if (String.valueOf(string.charAt(i)).equals("A")||
				String.valueOf(string.charAt(i)).equals("E")||
				String.valueOf(string.charAt(i)).equals("I")||
				String.valueOf(string.charAt(i)).equals("O")||
				String.valueOf(string.charAt(i)).equals("U")||
				String.valueOf(string.charAt(i)).equals("L")||
				String.valueOf(string.charAt(i)).equals("N")||
				String.valueOf(string.charAt(i)).equals("R")||
				String.valueOf(string.charAt(i)).equals("S")||
				String.valueOf(string.charAt(i)).equals("T"))
				score+=1;
			else if (String.valueOf(string.charAt(i)).equals("D")||
					String.valueOf(string.charAt(i)).equals("G"))
				score+=2;
			else if (String.valueOf(string.charAt(i)).equals("B")||
					String.valueOf(string.charAt(i)).equals("C")||
					String.valueOf(string.charAt(i)).equals("M")||
					String.valueOf(string.charAt(i)).equals("P"))
				score+=3;
			else if (String.valueOf(string.charAt(i)).equals("F")||
					String.valueOf(string.charAt(i)).equals("H")||
					String.valueOf(string.charAt(i)).equals("V")||
					String.valueOf(string.charAt(i)).equals("W")||
					String.valueOf(string.charAt(i)).equals("Y"))
				score+=4;
			else if (String.valueOf(string.charAt(i)).equals("K"))
				score+=5;
			else if (String.valueOf(string.charAt(i)).equals("J")||
					String.valueOf(string.charAt(i)).equals("X"))
				score+=8;
			else if (String.valueOf(string.charAt(i)).equals("Q")||
					String.valueOf(string.charAt(i)).equals("Z"))
				score+=10;
			}
		return score;
	}

	/**
	 * DONE
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
		String phoneNumber = "";
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				phoneNumber = phoneNumber.concat(Character.toString(string.charAt(i)));
			}
		}
		if (phoneNumber.length()<=9) {
			throw new IllegalArgumentException("Invalid phone number entered");
		}
		else if (phoneNumber.length()==11) {
			if (string.charAt(0)=='1') {
				phoneNumber = phoneNumber.substring(1);
			}
			else {
				throw new IllegalArgumentException("Invalid phone number entered");
			}
		}
		else if (phoneNumber.length()>11) {
			throw new IllegalArgumentException("Invalid phone number entered");
		}
		return phoneNumber;
	}
	/**
	 *DONE 
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		HashMap<String, Integer> wordsInPhrase = new HashMap<String, Integer>();
		string = string.replaceAll("\\n", "");
		String[] splitPhrase = string.split("\\s|\\t|,");
		int key = 0;
		for (int i = 0; i < splitPhrase.length; i ++) {
			if (wordsInPhrase.containsKey(splitPhrase[i])) {
				key = wordsInPhrase.get(splitPhrase[i]);
				wordsInPhrase.put(splitPhrase[i], key + 1);
			}
			else {
				wordsInPhrase.put(splitPhrase[i], 1);
			}
		}
		return wordsInPhrase;
	}

	/**
	 * DONE
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int lowValue = 0;
			int highValue = getSortedList().size()-1;
			while (lowValue <= highValue) {
				int middleValue = (lowValue + highValue) / 2;
				if (sortedList.get(middleValue).equals(t)) {
					return middleValue;
				} else if ((int) sortedList.get(middleValue) > (int) t) {
					highValue = middleValue - 1;
				} else if ((int) sortedList.get(middleValue) < (int) t) {
					lowValue = middleValue + 1;
				}
			}
			return 0;
		}
		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * DONE
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String[] splitPhrase = string.split(" ");
		String pigLatin = "";
		for (int i = 0; i < splitPhrase.length; i++) {
			if (i >= 1) {
				pigLatin = pigLatin.concat(" ");
			}
			char firstLetter = splitPhrase[i].charAt(0);
			System.out.println(firstLetter);
			while (Character.toString(firstLetter).matches("[aeiou]")==false) {
				if (Character.toString(firstLetter).matches("[q]")){
					String prefix = splitPhrase[i].substring(0, 2);
					String suffix = splitPhrase[i].substring(2);
					splitPhrase[i] = suffix.concat(prefix);
					firstLetter = splitPhrase[i].charAt(0);
				}
				else {
					String prefix = splitPhrase[i].substring(0, 1);
					String suffix = splitPhrase[i].substring(1);
					splitPhrase[i] = suffix.concat(prefix);
					firstLetter = splitPhrase[i].charAt(0);
				}
			}
			pigLatin = pigLatin.concat(splitPhrase[i].concat("ay"));
		}
		System.out.println(pigLatin);
		return pigLatin;
	}
	/**
	 * DONE
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		double armstrong = 0;
		double currentDigit = 0;
		int inputToBeSplit = input;
		int numDigits = (int) (Math.log10(input) + 1);
		for (int i = 0; i < numDigits; i++) {
			currentDigit = inputToBeSplit % 10;
			armstrong = armstrong + Math.pow(currentDigit, numDigits);
			inputToBeSplit = inputToBeSplit / 10;
		}
		if (armstrong == input) {
			return true;
		} else
			return false;
	}
	/**
	 * DONE
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<Long>();
		for (long i = 2; i < l; i++) {
			while ((l % i) == 0) {
				primeFactors.add(Long.valueOf(i));
				l = l/i;
			}
		}
		if (l != 1) {
			primeFactors.add(Long.valueOf(l));
		}
		return primeFactors;
	}

	/**
	 * DONE
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;
		public static final String LOWERCASEALPHABET = "abcdefghijklmnopqrstuvwxyz";
		public static final String UPPERCASEALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuffer cipherText = new StringBuffer();
			for (int i=0; i<string.length(); i++) {
				if(Character.isUpperCase(string.charAt(i))==true) {
					 int plainTextCharIndex = UPPERCASEALPHABET.indexOf(string.charAt(i));
					 int rotateIndex = (key + plainTextCharIndex) % 26;
					 char cipherChar = UPPERCASEALPHABET.charAt(rotateIndex);
					 cipherText.append(cipherChar);
				} else if(Character.isLowerCase(string.charAt(i))==true) {
					 int plainTextCharIndex = LOWERCASEALPHABET.indexOf(string.charAt(i));
					 int rotateIndex = (key + plainTextCharIndex) % 26;
					 char cipherChar = LOWERCASEALPHABET.charAt(rotateIndex);
					 cipherText.append(cipherChar);
				} else {
					 cipherText.append(string.charAt(i));
				}
			}
			String cipherTextAsString = cipherText.toString();
			return cipherTextAsString;
		}
	}

	/**
	 * DONE but probably extremely suboptimal - final test takes ~30 seconds
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	public int calculateNthPrime(int i) throws IllegalArgumentException {
		if (i == 0) {
			throw new IllegalArgumentException("Invalid number entered");
		}
		int nthPrime = 0;
		int checkIfPrime = 2;
		for (int j = 1; j <= i; j++) {
			boolean isNotPrime = false;
			while (isNotPrime==false) {
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

	/**
	 * DONE
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String lowerCaseString = string.toLowerCase();
			lowerCaseString = lowerCaseString.replaceAll("[^a-zA-Z0-9]","");
			StringBuffer cipherText = new StringBuffer();
			int characterCount = 0;
			for (int i = 0; i < lowerCaseString.length(); i++) {
				char plainTextChar = lowerCaseString.charAt(i);
				switch(plainTextChar) {
					case 'a':
						cipherText.append('z');
						break;
					case 'b':
						cipherText.append('y');
						break;
					case 'c':
						cipherText.append('x');
						break;
					case 'd':
						cipherText.append('w');
						break;
					case 'e':
						cipherText.append('v');
						break;
					case 'f':
						cipherText.append('u');
						break;
					case 'g':
						cipherText.append('t');
						break;
					case 'h':
						cipherText.append('s');
						break;
					case 'i':
						cipherText.append('r');
						break;
					case 'j':
						cipherText.append('q');
						break;
					case 'k':
						cipherText.append('p');
						break;
					case 'l':
						cipherText.append('o');
						break;
					case 'm':
						cipherText.append('n');
						break;
					case 'n':
						cipherText.append('m');
						break;
					case 'o':
						cipherText.append('l');
						break;
					case 'p':
						cipherText.append('k');
						break;
					case 'q':
						cipherText.append('j');
						break;
					case 'r':
						cipherText.append('i');
						break;
					case 's':
						cipherText.append('h');
						break;
					case 't':
						cipherText.append('g');
						break;
					case 'u':
						cipherText.append('f');
						break;
					case 'v':
						cipherText.append('e');
						break;
					case 'w':
						cipherText.append('d');
						break;
					case 'x':
						cipherText.append('c');
						break;
					case 'y':
						cipherText.append('b');
						break;
					case 'z':
						cipherText.append('a');
						break;
					case '1':
						cipherText.append('1');
						break;
					case '2':
						cipherText.append('2');
						break;
					case '3':
						cipherText.append('3');
						break;
					case '4':
						cipherText.append('4');
						break;
					case '5':
						cipherText.append('5');
						break;
					case '6':
						cipherText.append('6');
						break;
					case '7':
						cipherText.append('7');
						break;
					case '8':
						cipherText.append('8');
						break;
					case '9':
						cipherText.append('9');
						break;
					case '0':
						cipherText.append('0');
						break;
				}
				characterCount++;
				if (characterCount == 5) {
					cipherText.append(' ');
					characterCount = 0;
				}
			}
			if (cipherText.charAt(cipherText.length()-1)==' ') {
				cipherText.deleteCharAt(cipherText.length()-1);
			}
			String cipherTextAsString = cipherText.toString();
			return cipherTextAsString;
		}
	

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String removeSpaces = string.replaceAll("[^a-zA-Z0-9]","");
			StringBuffer plainText = new StringBuffer();
			int characterCount = 0;
			for (int i = 0; i < removeSpaces.length(); i++) {
				char cipherTextChar = removeSpaces.charAt(i);
				switch(cipherTextChar) {
					case 'a':
						plainText.append('z');
						break;
					case 'b':
						plainText.append('y');
						break;
					case 'c':
						plainText.append('x');
						break;
					case 'd':
						plainText.append('w');
						break;
					case 'e':
						plainText.append('v');
						break;
					case 'f':
						plainText.append('u');
						break;
					case 'g':
						plainText.append('t');
						break;
					case 'h':
						plainText.append('s');
						break;
					case 'i':
						plainText.append('r');
						break;
					case 'j':
						plainText.append('q');
						break;
					case 'k':
						plainText.append('p');
						break;
					case 'l':
						plainText.append('o');
						break;
					case 'm':
						plainText.append('n');
						break;
					case 'n':
						plainText.append('m');
						break;
					case 'o':
						plainText.append('l');
						break;
					case 'p':
						plainText.append('k');
						break;
					case 'q':
						plainText.append('j');
						break;
					case 'r':
						plainText.append('i');
						break;
					case 's':
						plainText.append('h');
						break;
					case 't':
						plainText.append('g');
						break;
					case 'u':
						plainText.append('f');
						break;
					case 'v':
						plainText.append('e');
						break;
					case 'w':
						plainText.append('d');
						break;
					case 'x':
						plainText.append('c');
						break;
					case 'y':
						plainText.append('b');
						break;
					case 'z':
						plainText.append('a');
						break;
					case '1':
						plainText.append('1');
						break;
					case '2':
						plainText.append('2');
						break;
					case '3':
						plainText.append('3');
						break;
					case '4':
						plainText.append('4');
						break;
					case '5':
						plainText.append('5');
						break;
					case '6':
						plainText.append('6');
						break;
					case '7':
						plainText.append('7');
						break;
					case '8':
						plainText.append('8');
						break;
					case '9':
						plainText.append('9');
						break;
					case '0':
						plainText.append('0');
						break;
				}
			}
			if (plainText.charAt(plainText.length()-1)==' ') {
				plainText.deleteCharAt(plainText.length()-1);
			}
			String plainTextAsString = plainText.toString();
			return plainTextAsString;
		}
	}

	/**
	 * DONE
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		int validityCheck = 0;
		int checkDigit = 10;
		boolean containsIllegalCharacter = false;
		StringBuilder isbn = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				isbn.append(string.charAt(i));
			}
		}
		if (isbn.length() < 10) {
			if (string.charAt(string.length()-1) == 'X') {
				isbn.append('X');
			}
			else {
				boolean containsIllegalCharcter = true;
			}
		}
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isDigit(isbn.charAt(i))) {
				validityCheck = validityCheck + (Integer.parseInt(String.valueOf(isbn.charAt(i))) * checkDigit);
				checkDigit--;
			}else if (isbn.charAt(isbn.length()-1)=='X') {
				validityCheck += 10;
			}
		}
		validityCheck = validityCheck % 11;
		if (validityCheck == 0) {
			return true;
		}
		if (containsIllegalCharacter == true) {
			return false;
		}
		return false;
	}

	/**
	 * DONE
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		StringBuilder pangramCheck = new StringBuilder();
		pangramCheck.append("abcdefghijklmnopqrstuvwxyz");
		char charCheck;
		string = string.toLowerCase();
		System.out.println(string);
		StringBuilder noSpaces = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetter(string.charAt(i))) {
				noSpaces.append(string.charAt(i));
			}
		}
		System.out.println(noSpaces);
		for (int i = 0; i < noSpaces.length(); i++) {
			charCheck = noSpaces.charAt(i);
			for (int j = 0; j < pangramCheck.length(); j++) {
				if (pangramCheck.charAt(j)==charCheck) {
					pangramCheck.deleteCharAt(j);
				}
			}
		}
		if (pangramCheck.toString().isEmpty()) {
			System.out.println(true);
			return true;
		} else {
			System.out.println(false);
			return false;
		}
	}
	/**
	 * DONE
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	
	private static final long GIGASECONDS = 1000000000;
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime addGS = LocalDateTime.of(1, 1, 1, 0, 0, 0);
		addGS = addGS.withYear(given.get(ChronoField.YEAR)).withMonth(given.get(ChronoField.MONTH_OF_YEAR)).withDayOfMonth(given.get(ChronoField.DAY_OF_MONTH));
		try {
			addGS = addGS.withHour(given.get(ChronoField.HOUR_OF_DAY)).withMinute(given.get(ChronoField.MINUTE_OF_HOUR)).withSecond(given.get(ChronoField.SECOND_OF_MINUTE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		addGS = addGS.plusSeconds(GIGASECONDS);
		return addGS;
	}
	/**
	 * DONE
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		List<String> multiples = new ArrayList<String>();
		int sum = 0;
		for (int input = 0; input < set.length; input++) {
			for (int checkIfFactor = 1; checkIfFactor < i; checkIfFactor++) {
				if (checkIfFactor % set[input] == 0) {
					String checkForDoubles = Integer.toString(checkIfFactor);
					if (multiples.contains(checkForDoubles)==false) {
						System.out.println("adding: " + checkIfFactor);
						multiples.add(Integer.toString(checkIfFactor));
					}
				}
			}	
		}
		for (int j = 0; j < multiples.size(); j++) {
			sum = sum + Integer.parseInt(multiples.get(j).toString());
		}
		System.out.println(sum);
		return sum;
	}

	/**
	 * DONE
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		String stringOnlyNumbers = string.replaceAll("[^a-zA-Z0-9]", "");
		StringBuffer sb = new StringBuffer();
		String stringReversed = sb.append(stringOnlyNumbers).reverse().toString();
		int luhn = 0;
		int currentDigit = 0;
		boolean checkForLetters = false;
		boolean luhnCheck = false;
		for (int i = 0; i < stringReversed.toString().length(); i++) {
			if (Character.isDigit(stringReversed.charAt(i))==true){
				if ((i % 2 == 0)&&(i != 0)) {
					currentDigit = (Integer.parseInt(String.valueOf(stringReversed.charAt(i)))) * 2;
					if (currentDigit > 9) {
						currentDigit-=9;
						luhn = luhn + currentDigit;
					}
				} else {
					currentDigit = Integer.parseInt(String.valueOf(stringReversed.charAt(i)));
					luhn = luhn + currentDigit;
				}
			} else {
				checkForLetters = true;
			}
			
		}
		if (luhn % 10 == 0) {
			luhnCheck = true;
		}
		if (checkForLetters==true) {
			luhnCheck = false;
		}
		return luhnCheck;
	}

	/**
	 * DONE
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		List<String> numbers = new ArrayList<String>();
		int finalAnswer = 0;
		StringBuffer numberBuilder = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			numberBuilder.delete(0, numberBuilder.capacity());
			if (i > 1) {
				if (string.charAt(i-1)=='-') {
					numberBuilder.append('-');
				}
			}
			if ((Character.isDigit(string.charAt(i))==true)) {
				numberBuilder = numberBuilder.append(string.charAt(i));
				while (Character.isDigit(string.charAt(i+1))==true) {
					i++;
					numberBuilder = numberBuilder.append(string.charAt(i)); 
				}
			numbers.add(numberBuilder.toString());	
			}
		}
		if (string.contains("plus")==true) {
			finalAnswer = Integer.parseInt(numbers.get(0).toString()) + Integer.parseInt(numbers.get(1).toString());
		}
		else if (string.contains("minus")==true) {
			finalAnswer = Integer.parseInt(numbers.get(0).toString()) - Integer.parseInt(numbers.get(1).toString());
		}
		else if (string.contains("multipl")==true) {
			finalAnswer = Integer.parseInt(numbers.get(0).toString()) * Integer.parseInt(numbers.get(1).toString());
		}
		else if (string.contains("divid")==true) {
			finalAnswer = Integer.parseInt(numbers.get(0).toString()) / Integer.parseInt(numbers.get(1).toString());
		}
		return finalAnswer;
	}
}
