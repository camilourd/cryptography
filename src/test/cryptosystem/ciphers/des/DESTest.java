package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import cryptosystem.Cryptosystem;
import cryptosystem.ciphers.des.DES;
import test.cryptosystem.CryptosystemTest;
import unalcol.types.collection.bitarray.BitArray;

public class DESTest extends CryptosystemTest<BitArray, BitArray, Boolean> {
	
	public BitArray key, input, output;
	
	public DESTest(Cryptosystem<BitArray, BitArray, Boolean> cryptosystem) {
		super(new DES());
		key = cryptosystem.generateKey();
		input = new BitArray("0000000100100011010001010110011110001001101010111100110111101111");
	}

	@Override
	public void encodeDecodeTest() {
		BitArray result = cryptosystem.encode(key, input);
		System.out.println(key);
		assertNotEquals(input.toString(), result.toString());
		result = cryptosystem.decode(key, result);
		assertEquals(input.toString(), result.toString());
	}

}
