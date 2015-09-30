package tools;

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
	
}
