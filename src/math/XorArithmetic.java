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
	
	public static int xmultiply(int b, int x, int m) {
		int res = 0;
		for(; x > 0; x >>= 1) {
			if((x & 1) > 0)
				res ^= b;
			b = XorArithmetic.mod(b << 1, m);
		}
		return res;
	}

}
