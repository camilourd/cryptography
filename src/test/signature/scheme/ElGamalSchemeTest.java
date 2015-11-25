package test.signature.scheme;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import cryptography.cryptosystem.ciphers.elgamal.key.ElGamalKey;
import cryptography.signature.function.sha.SHA256;
import cryptography.signature.scheme.ElGamalScheme;

public class ElGamalSchemeTest {

	protected ElGamalScheme scheme;
	protected ElGamalKey key;
	
	public ElGamalSchemeTest() {
		scheme = new ElGamalScheme(new SHA256());
		key = new ElGamalKey(
				new BigInteger("961748941"),
				new BigInteger("134567"),
				new BigInteger("5487231")
			);
	}

	@Test
	public void test() {
		assertEquals(true, scheme.verify(key, scheme.sign(key, "pass")));
	}

}
