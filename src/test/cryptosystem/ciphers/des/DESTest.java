package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import cryptosystem.ciphers.des.DES;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESTest extends CryptosystemTest<BitArray, BitArray> {
	
	public BitArray key, input;
	
	public DESTest() {
		super(new DES());
		key = cryptosystem.generateKey();
		input = new BitArray("0000000100100011010001010110011110001001101010111100110111101111");
	}

	@Override
	public void encodeDecodeTest() {
		BitArray result = cryptosystem.encode(key, input);
		assertNotEquals(input.toString(), result.toString());
		result = cryptosystem.decode(key, result);
		assertEquals(input.toString(), result.toString());
	}

}
