package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptosystem.ciphers.AESCipher;
import test.cryptosystem.CryptosystemTest;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class AESCipherTest extends CryptosystemTest<BitArray, String, Character> {
	
	public AESCipherTest() {
		super(new AESCipher(128, 283));
	}

	@Override
	public void encodeDecodeTest() {
		String message = "this is a sample test for AES cipher";
		System.out.println("input:    " + message);
		BitArray key = cryptosystem.generateKey();
		System.out.println("key:      " + BitArrayTools.toHexString(key));
		String output = cryptosystem.encode(key, message);
		assertNotEquals(message, output.substring(0, message.length()));
		System.out.println("encoding: " + output);
		output = cryptosystem.decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
