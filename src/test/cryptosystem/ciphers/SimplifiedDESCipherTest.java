package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptography.cryptosystem.ciphers.SimplifiedDESCipher;
import test.cryptosystem.BlockCryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDESCipherTest extends BlockCryptosystemTest<BitArray, String, BitArray> {

	public BitArray key;
	
	public SimplifiedDESCipherTest() {
		super(new SimplifiedDESCipher());
		key = new BitArray("111000111");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = cryptosystem.encode(key, message);
		assertNotEquals(message, output.substring(0, message.length()));
		output = cryptosystem.decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
