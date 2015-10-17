package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptosystem.ciphers.SimplifiedDESCipher;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDESCipherTest extends CryptosystemTest<BitArray, String, Character> {

	public BitArray key;
	
	public SimplifiedDESCipherTest() {
		super(new SimplifiedDESCipher());
		key = new BitArray("111000111");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = ((SimplifiedDESCipher)cryptosystem).encode(key, message);
		assertNotEquals(message, output.substring(0, message.length()));
		output = ((SimplifiedDESCipher)cryptosystem).decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
