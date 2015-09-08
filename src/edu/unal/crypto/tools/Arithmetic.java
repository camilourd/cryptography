package edu.unal.crypto.tools;

public class Arithmetic {

	public static int gcd(int a, int b) {
		int r;
		do {
			r = a % b;
			a = b;
			b = r;
		} while(r > 0);
		return a;
	}
	
	public static boolean areCoprimes(int a, int b) {
		return gcd(a, b) == 1;
	}
	
}
