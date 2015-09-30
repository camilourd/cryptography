package math;

public class XorArithmetic {
	
	public static int multiply(int a, int b) {
		int result = 0, idx;
		while(b > 0) {
			idx = (b & -b);
			result ^= a * idx;
			b -= idx;
		}
		return result;
	}
	
	public static int mod(int a, int m) {
		int idx = 1;
		while((idx<<1) <= m) idx <<= 1;
		
		while(a >= m) {
			int d = m, c = idx;
			while((c<<1) <= a){ c <<= 1; d <<= 1;}
			a ^= d;
		}
		return a;
	}

}
