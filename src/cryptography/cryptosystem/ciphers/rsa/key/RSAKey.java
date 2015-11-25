package cryptography.cryptosystem.ciphers.rsa.key;

import java.math.BigInteger;

public class RSAKey {
	
	public PublicKey publicKey;
	public PrivateKey privateKey;
	
	public RSAKey(BigInteger p, BigInteger q, BigInteger e) {
		this.privateKey = new PrivateKey(p, q, e);
		this.publicKey = new PublicKey(p, q, e);
	}

	public RSAKey(PublicKey publicKey, PrivateKey privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
}
