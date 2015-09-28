package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import alphabet.alphabets.DESAlphabet;
import cryptosystem.ciphers.DESCipher;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESCipherTest extends CryptosystemTest {

	BitArray key;
	
	public DESCipherTest() {
		super(new DESCipher(), new DESAlphabet());
		key = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = ((DESCipher)cryptosystem).encode(key, message);
		output = ((DESCipher)cryptosystem).decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
