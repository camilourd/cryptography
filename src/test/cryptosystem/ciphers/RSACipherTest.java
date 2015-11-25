package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import java.math.BigInteger;

import cryptography.cryptosystem.ciphers.RSACipher;
import cryptography.cryptosystem.ciphers.rsa.key.RSAKey;
import test.cryptosystem.BlockCryptosystemTest;

public class RSACipherTest extends BlockCryptosystemTest<RSAKey, String, BigInteger> {

	public RSACipherTest() {
		super(new RSACipher());
	}

	@Override
	public void encodeDecodeTest() {
		System.out.println("RSA Tests");
		RSAKey key = new RSAKey(
					new BigInteger("961748941"),
					new BigInteger("982451653"),
					new BigInteger("3260981")
				);
		String message = "this is a sample test for RSA Cipher";
		System.out.println("input:    " + message);
		String output = cryptosystem.encode(key, message);
		System.out.println("encoding: " + output);
		assertNotEquals(message, output);
		output = cryptosystem.decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output);
		
		message = "Este es un mensaje de prueba para mostrar";
		System.out.println("input:    " + message);
		output = cryptosystem.encode(key, message);
		System.out.println("encoding: " + output);
		assertNotEquals(message, output);
		output = cryptosystem.decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output);
		
		message = "thisisjustatest";
		System.out.println("input:    " + message);
		output = cryptosystem.encode(key, message);
		System.out.println("encoding: " + output);
		assertNotEquals(message, output);
		output = cryptosystem.decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output);
	}

}
