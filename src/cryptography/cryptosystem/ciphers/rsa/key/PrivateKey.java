package cryptography.cryptosystem.ciphers.rsa.key;

import java.math.BigInteger;

public class PrivateKey {
	
	public BigInteger e;
	public BigInteger n;
	public BigInteger phi;
	
	public PrivateKey(BigInteger p, BigInteger q, BigInteger e) {
		this.e = e;
		this.n = p.multiply(q);
		this.phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	}
	
	public boolean isValid() {
		return e.gcd(phi).equals(BigInteger.ONE);
	}

}
