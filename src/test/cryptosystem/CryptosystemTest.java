package test.cryptosystem;

import org.junit.Test;

import edu.unal.crypto.cryptosystem.*;

public abstract class CryptosystemTest {

	protected Cryptosystem<?, ?, ?> cryptosystem;
	
	public CryptosystemTest(Cryptosystem<?, ?, ?> cryptosystem) {
		this.cryptosystem = cryptosystem;
	}
	
	@Test
	public abstract void encodeDecodeTest();
	
}
