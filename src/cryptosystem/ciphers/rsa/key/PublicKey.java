package cryptosystem.ciphers.rsa.key;

import java.math.BigInteger;

public class PublicKey {
	
	public BigInteger d;
	public BigInteger n;
	
	public PublicKey(BigInteger p, BigInteger q, BigInteger e) {
		this.n = p.multiply(q);
		this.d = e.modInverse((p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)));
	}

	public PublicKey(BigInteger d, BigInteger n) {
		this.d = d;
		this.n = n;
	}
	
}
