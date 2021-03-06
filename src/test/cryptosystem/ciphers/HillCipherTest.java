package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptography.alphabet.alphabets.EnglishAlphabet;
import cryptography.cryptosystem.ciphers.HillCipher;
import flanagan.math.Matrix;
import test.cryptosystem.CryptosystemTest;

public class HillCipherTest extends CryptosystemTest<Matrix, String> {

	public HillCipherTest() {
		super(new HillCipher(new EnglishAlphabet()));
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		Matrix key = new Matrix(3, 3);
		key.setElement(0, 0, 5); key.setElement(0, 1, 17); key.setElement(0, 2, 20);
		key.setElement(1, 0, 9); key.setElement(1, 1, 23); key.setElement(1, 2, 3);
		key.setElement(2, 0, 2); key.setElement(2, 1, 11); key.setElement(2, 2, 13);
		String output = cryptosystem.encode(key, message);
		assertEquals(message, cryptosystem.decode(key, output));
	}

	

}
