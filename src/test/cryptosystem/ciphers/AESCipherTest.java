package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import alphabet.alphabets.ExtendedAlphabet;
import cryptosystem.ciphers.AESCipher;
import test.cryptosystem.CryptosystemTest;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class AESCipherTest extends CryptosystemTest {
	
	public AESCipherTest() {
		super(new AESCipher(128, 283), new ExtendedAlphabet());
	}

	@Override
	public void encodeDecodeTest() {
		String message = "this is a sample test for AES cipher";
		System.out.println("input:    " + message);
		BitArray key = ((AESCipher)cryptosystem).generateKey();
		System.out.println("key:      " + BitArrayTools.toHexString(key));
		String output = ((AESCipher)cryptosystem).encode(key, message);
		assertNotEquals(message, output.substring(0, message.length()));
		System.out.println("encoding: " + output);
		output = ((AESCipher)cryptosystem).decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
