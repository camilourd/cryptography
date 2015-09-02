package test.cryptosystem;

import org.junit.Test;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public abstract class CryptosystemTest {

	protected Alphabet alphabet;
	protected Cryptosystem<?, ?> cryptosystem;
	
	public CryptosystemTest(Cryptosystem<?, ?> cryptosystem, Alphabet alphabet) {
		this.cryptosystem = cryptosystem;
		this.alphabet = alphabet;
	}
	
	@Test
	public abstract void encodeTest();
	@Test
	public abstract void decodeTest();

}
