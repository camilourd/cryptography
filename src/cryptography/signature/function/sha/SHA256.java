package cryptography.signature.function.sha;

import cryptography.signature.HashFunction;

public class SHA256 implements HashFunction<String, String> {
	
	@Override
	public String hash(String key) {
		int[][] m = preprocess(key + ((char)128));
		int[] h = compute(m);
		String res = "";
		for(int i = 0; i < h.length; ++i)
			res += toHexStr(h[i]);
		return res;
	}

	private int[][] preprocess(String msg) {
		int l = (msg.length() / 4) + 2;
		int n = (int)Math.ceil(((double)l) / 16.0);
		int[][] m = new int[n][16];
		
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < 16; ++j)
				for(int k = 0; k < 4; ++k) {
					int p = (i * 64) + (j * 4) + k;
					if(p < msg.length())
						m[i][j] |= (((int)msg.charAt(p)) << (24 - (8 * k)));
				}
		m[n-1][14] = (int)Math.floor(((msg.length()-1)*8) / Math.pow(2, 32));
	    m[n-1][15] = ((msg.length()-1)*8) & 0xffffffff;
		return m;
	}
	
	private int[] compute(int[][] m) {
		int[] K = {
				0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
		        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
		        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
		        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
		        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
		        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
		        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
		        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
			};
		int[] H = {
				0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
				0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
			};
		
		int[] w = new int[64];
		int a, b, c, d, e, f, g, h;
		for(int i = 0; i < m.length; ++i) {
			for(int j = 0; j < 16; ++j)
				w[j] = m[i][j];
			for(int j = 16; j < 64; ++j)
				w[j] = (sigma1(w[j-2]) + w[j-7] + sigma0(w[j-15]) + w[j-16]) & 0xffffffff;
			
			a = H[0]; b = H[1]; c = H[2]; d = H[3]; e = H[4]; f = H[5]; g = H[6]; h = H[7];
			
			for(int j = 0; j < 64; ++j) {
				int t1 = h + sum1(e) + ch(e, f, g) + K[j] + w[j];
				int t2 = sum0(a) + maj(a, b, c);
				h = g;
				g = f;
				f = e;
				e = (d + t1) & 0xffffffff;
				d = c;
				c = b;
				b = a;
				a = (t1 + t2) & 0xffffffff;
			}
			
			H[0] = (H[0] + a) & 0xffffffff;
	        H[1] = (H[1] + b) & 0xffffffff; 
	        H[2] = (H[2] + c) & 0xffffffff; 
	        H[3] = (H[3] + d) & 0xffffffff; 
	        H[4] = (H[4] + e) & 0xffffffff;
	        H[5] = (H[5] + f) & 0xffffffff;
	        H[6] = (H[6] + g) & 0xffffffff; 
	        H[7] = (H[7] + h) & 0xffffffff;
		}
		return H;
	}
	
	public int sigma0(int x) {
		return rotr(7, x) ^ rotr(18, x) ^ (x >>> 3);
	}
	
	public int sigma1(int x) {
		return rotr(17, x) ^ rotr(19, x) ^ (x >>> 10);
	}
	
	public int sum0(int x) {
		return rotr(2, x) ^ rotr(13, x) ^ rotr(22, x);
	}
	
	public int sum1(int x) {
		return rotr(6, x) ^ rotr(11, x) ^ rotr(25, x);
	}
	
	public int rotr(int n, int x) {
		return (x >>> n) | (x << (32 - n));
	}
	
	public int ch(int x, int y, int z) {
		return (x & y) ^ (~x & z);
	}
	
	public int maj(int x, int y, int z) {
		return (x & y) ^ (x & z) ^ (y & z);
	}
	
	public String toHexStr(int n) {
		String s = "";
		int v;
		for(int i = 7; i >= 0; --i) {
			v = (n >>> (i * 4)) & 0xf;
			s += toString(v);
		}
		return s;
	}
	
	public String toString(int v) {
		if(v == 0) return "0";
		String res = "";
		for(int n = v; n > 0; n /= 16) {
			int c = n % 16;
			if(c < 10)
				res = c + res;
			else
				res = (char)('a' + c - 10) + res;
		}
		return res;
	}

	@Override
	public boolean verify(String key, String signed) {
		return signed.equals(hash(key));
	}

}
