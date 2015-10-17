package cryptosystem.ciphers.rsa;

import java.math.BigInteger;

import alphabet.alphabets.NaturalAlphabet;
import cryptosystem.Cryptosystem;
import cryptosystem.ciphers.rsa.key.RSAKey;

public class RSA extends Cryptosystem<RSAKey, BigInteger> {
	
	public RSA() {
		super(new NaturalAlphabet());
	}

	@Override
	public BigInteger encode(RSAKey key, BigInteger message) {
		return message.modPow(key.privateKey.e, key.privateKey.n);
	}

	@Override
	public BigInteger decode(RSAKey key, BigInteger message) {
		return message.modPow(key.publicKey.d, key.publicKey.n);
	}

	@Override
	public boolean isValidKey(RSAKey key) {
		return (key.publicKey.d.modInverse(key.publicKey.n)).equals(key.privateKey.e)
				&& key.publicKey.n.equals(key.privateKey.n)
				&& key.publicKey.n.toString().length() > 5;
	}

	@Override
	public RSAKey generateKey() {
		return null;
	}
	
	

}
