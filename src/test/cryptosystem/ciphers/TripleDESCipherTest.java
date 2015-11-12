package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptosystem.ciphers.TripleDESCipher;
import test.cryptosystem.BlockCryptosystemTest;
import types.Pair;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDESCipherTest extends BlockCryptosystemTest<Pair<BitArray, BitArray>, String, BitArray> {

	protected Pair<BitArray, BitArray> key;

	public TripleDESCipherTest() {
		super(new TripleDESCipher());
		key = new Pair<BitArray, BitArray>(
				new BitArray("0001001100110100010101110111100110011011101111001101111111110001"),
				new BitArray("1110001011000100110010100110100011111110101101110111100110111111"));
	}

	@Override
	public void encodeDecodeTest() {
		System.out.println("Triple DES test");
		String message = "this is a sample message for Tiple DES cipher";
		System.out.println("message:  " + message);
		String output = cryptosystem.encode(key, message);
		System.out.println("encoding: " + output);
		assertNotEquals(message, output.substring(0, message.length()));
		output = cryptosystem.decode(key, output);
		System.out.println("decoding: " + output);
		assertEquals(message, output.substring(0, message.length()));
	}

}
