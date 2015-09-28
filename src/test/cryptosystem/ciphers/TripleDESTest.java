package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import alphabet.alphabets.DESAlphabet;
import cryptosystem.ciphers.TripleDES;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDESTest extends CryptosystemTest {

	BitArray[] key;

	public TripleDESTest() {
		super(new TripleDES(), new DESAlphabet());
		key = new BitArray[2];
		key[0] = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
		key[1] = new BitArray("1110001011000100110010100110100011111110101101110111100110111111");
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String output = ((TripleDES)cryptosystem).encode(key, message);
		output = ((TripleDES)cryptosystem).decode(key, output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
