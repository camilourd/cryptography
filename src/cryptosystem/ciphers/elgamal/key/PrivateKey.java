package cryptosystem.ciphers.elgamal.key;

import java.math.BigInteger;

public class PrivateKey {
	
	public BigInteger b;

	public PrivateKey(BigInteger b) {
		this.b = b;
	}

}
