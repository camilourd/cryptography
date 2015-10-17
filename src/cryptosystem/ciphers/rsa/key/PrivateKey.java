package cryptosystem.ciphers.rsa.key;

import java.math.BigInteger;

public class PrivateKey {
	
	public BigInteger e;
	public BigInteger n;
	
	public PrivateKey(BigInteger p, BigInteger q, BigInteger e) {
		this.e = e;
		this.n = p.multiply(q);
	}

	public PrivateKey(BigInteger e, BigInteger n) {
		this.e = e;
		this.n = n;
	}

}
