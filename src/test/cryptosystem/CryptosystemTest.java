package test.cryptosystem;

import org.junit.Test;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public abstract class CryptosystemTest {

	protected Cryptosystem<?, ?, ?> cryptosystem;
	
	public CryptosystemTest(Cryptosystem<?, ?, ?> cryptosystem) {
		this.cryptosystem = cryptosystem;
	}
	
	@Test
	public abstract void encodeDecodeTest();
	
}
