package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import java.math.BigInteger;

import cryptosystem.ciphers.ElGamalCipher;
import cryptosystem.ciphers.elgamal.key.ElGamalKey;
import test.cryptosystem.BlockCryptosystemTest;
import types.Pair;

public class ElGamalCipherTest extends BlockCryptosystemTest <ElGamalKey, String, Pair<BigInteger, BigInteger>> {

	protected ElGamalKey key;
	
	public ElGamalCipherTest() {
		super(new ElGamalCipher());
		key = new ElGamalKey(
				new BigInteger("961748941"),
				new BigInteger("134567"),
				new BigInteger("5487231")
			);
	}

	@Override
	public void encodeDecodeTest() {
		System.out.println("El Gamal Cipher Test");
		String message = "this is a sample test for El Gamal Cipher";
		System.out.println("message:  " + message);
		String encoding = cryptosystem.encode(key, message);
		System.out.println("encoding: " + encoding);
		assertNotEquals(message, encoding);
		String decoding = cryptosystem.decode(key, encoding);
		System.out.println("decoding: " + decoding);
		assertEquals(message, decoding);
	}

	

}
