package math;

public class ModularArithmetic {

	public static int multiplicativeInverse(int a, int n) {
		int b = n, d = 1, r = 1, p0 = 0, p1 = 1;
		for(int i = 0; r > 0; ++i) {
			if(i > 1) {
				r = modulo(p0 - (p1 * d), n);
				p0 = p1;
				p1 = r;
			}
			d = a / b;
			r = a % b;
			a = b;
			b = r;
		}
		return p1;
	}
	
	public static int modulo(int a, int n) {
		a = a % n;
		return (a < 0)? n + a : a;
	}
	
	public static int pow(int base, int exp, int modulus) {
		int result = 1;
		for(base %= modulus; exp > 0; exp >>= 1, base = (base * base) % modulus)
			if((exp & 1) == 1)
				result = (result * base) % modulus;
		return result;
	}
	
}
