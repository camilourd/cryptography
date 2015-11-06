package cryptosystem.ciphers.elgamal.key;

import java.math.BigInteger;

public class ElGamalKey {
	
	public PublicKey publicKey;
	public PrivateKey privateKey;
	
	public ElGamalKey(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ElGamalKey(BigInteger p, BigInteger g, BigInteger b) {
		this.publicKey = new PublicKey(p, g, g.modPow(b, p));
		this.privateKey = new PrivateKey(b);
	}

}
