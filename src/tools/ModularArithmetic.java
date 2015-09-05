package tools;

public class ModularArithmetic {

	public static int modInverse(int a, int n) {
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

}
