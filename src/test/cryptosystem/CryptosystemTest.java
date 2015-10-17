package test.cryptosystem;

import org.junit.Test;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public abstract class CryptosystemTest<K, M> {

	protected Alphabet alphabet;
	protected Cryptosystem<K, M> cryptosystem;
	
	public CryptosystemTest(Cryptosystem<K, M> cryptosystem) {
		this.cryptosystem = cryptosystem;
		this.alphabet = cryptosystem.getAlphabet();
	}
	
	@Test
	public abstract void encodeDecodeTest();
	
}
