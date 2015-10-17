package test.cryptosystem.ciphers;

import org.junit.Test;
import static org.junit.Assert.*;

import cryptosystem.ciphers.DESCipher;
import test.cryptosystem.BlockCryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESCipherTest extends BlockCryptosystemTest<BitArray, String, Character, BitArray> {

	BitArray key;
	
	public DESCipherTest() {
		super(new DESCipher());
		key = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
	}
	
	@Test
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = cryptosystem.encode(key, message);
		assertNotEquals(message, output);
		System.out.println(output);
		output = cryptosystem.decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
		System.out.println(output);
	}

}
