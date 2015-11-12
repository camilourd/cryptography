package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import cryptosystem.ciphers.des.TripleDES;
import test.cryptosystem.CryptosystemTest;
import types.Pair;
import unalcol.types.collection.bitarray.BitArray;

public class TripleDESTest extends CryptosystemTest<Pair<BitArray, BitArray>, BitArray> {

	Pair<BitArray, BitArray> key;
	
	public TripleDESTest() {
		super(new TripleDES());
		key = new Pair<BitArray, BitArray>(
				new BitArray("0001001100110100010101110111100110011011101111001101111111110001"),
				new BitArray("1110001011000100110010100110100011111110101101110111100110111111"));
	}

	@Override
	public void encodeDecodeTest() {
		BitArray input = new BitArray("0000000100100011010001010110011110001001101010111100110111101111");
		BitArray result = cryptosystem.encode(key, input);
		assertNotEquals(input.toString(), result.toString());
		result = cryptosystem.decode(key, result);
		assertEquals(input.toString(), result.toString());
	}	

}
