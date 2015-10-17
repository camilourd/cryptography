package test.cryptosystem;

import org.junit.Test;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public abstract class CryptosystemTest<K, M, C> {

	protected Alphabet<C> alphabet;
	protected Cryptosystem<K, M, C> cryptosystem;
	
	public CryptosystemTest(Cryptosystem<K, M, C> cryptosystem) {
		this.cryptosystem = cryptosystem;
		this.alphabet = cryptosystem.getAlphabet();
	}
	
	@Test
	public abstract void encodeDecodeTest();
	
}
