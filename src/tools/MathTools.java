package tools;

import java.math.BigInteger;
import java.util.ArrayList;

import math.Arithmetic;

public class MathTools {
	
	public static ArrayList<Integer> calculateCoprimes(int n) {
		ArrayList<Integer> coprimes = new ArrayList<Integer>();
		for(int i = 1; i < n; ++i)
			if(Arithmetic.areCoprimes(i, n))
				coprimes.add(i);
		return coprimes;
	}
	
	public static BigInteger generateBigInteger(int digits) {
		String result = "";
		for(int i = 0; i < digits; ++i)
			result += (char)(random(10) + '0');
		return new BigInteger(result);
	}
	
	public static int random(int size) {
		return (int)(Math.random() * size);
	}
	
}
