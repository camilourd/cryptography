package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptosystem.ciphers.TripleDES;
import test.cryptosystem.CryptosystemTest;
import types.Pair;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDESTest extends CryptosystemTest<Pair<BitArray, BitArray>, String, Character> {

	Pair<BitArray, BitArray> key;

	public TripleDESTest() {
		super(new TripleDES());
		key = new Pair<BitArray, BitArray>(
				new BitArray("0001001100110100010101110111100110011011101111001101111111110001"),
				new BitArray("1110001011000100110010100110100011111110101101110111100110111111"));
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
