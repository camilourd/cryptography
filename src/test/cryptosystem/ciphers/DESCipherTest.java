package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptosystem.ciphers.DESCipher;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESCipherTest extends CryptosystemTest<BitArray, String, Character> {

	BitArray key;
	
	public DESCipherTest() {
		super(new DESCipher());
		key = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = ((DESCipher)cryptosystem).encode(key, message);
		assertNotEquals(message, output.substring(0, message.length()));
		output = ((DESCipher)cryptosystem).decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
