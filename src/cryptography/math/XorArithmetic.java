package cryptography.math;

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
		
		while(a >= idx) {
			int d = m, c = idx;
			while((c<<1) <= a){ c <<= 1; d <<= 1;}
			a ^= d;
		}
		return a;
	}
	
	public static int multiply(int a, int b, int m) {
		return mod(multiply(a, b), m);
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
	
	public static int multiplicativeInverse(int a, int n) {
		int b = n, d = 1, r = 1, p0 = 0, p1 = 1;
		for(int i = 0; r > 0; ++i) {
			if(i > 1) {
				r = mod(p0 ^ (multiply(p1, d)), n);
				p0 = p1;
				p1 = r;
			}
			d = divide(a, b);
			r = mod(a, b);
			a = b;
			b = r;
		}
		return p1;
	}

	public static int divide(int a, int b) {
		int idx = 1, div= 0;
		while((idx<<1) <= b) idx <<= 1;
		
		while(a >= idx) {
			int d = b, c = idx, k = 1;
			while((c<<1) <= a){ c <<= 1; d <<= 1; k <<= 1;}
			a ^= d;
			div |= k;
		}
		return div;
	}

}
