package cryptography.cryptosystem.ciphers.elgamal.key;

import java.math.BigInteger;

public class PublicKey {
	
	public BigInteger p;
	public BigInteger g;
	public BigInteger gToTheb;
	
	public PublicKey(BigInteger p, BigInteger g, BigInteger gToTheb) {
		this.p = p;
		this.g = g;
		this.gToTheb = gToTheb;
	}	

}
