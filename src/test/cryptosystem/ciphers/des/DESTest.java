package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cryptosystem.ciphers.des.DES;
import unalcol.types.collection.bitarray.BitArray;

public class DESTest {
	
	public BitArray key, input, output;
	

	@Before
	public void setUp() throws Exception {
		key = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
		input = new BitArray("0000000100100011010001010110011110001001101010111100110111101111");
		output = new BitArray("1000010111101000000100110101010000001111000010101011010000000101");
	}

	@Test
	public void test() {
		BitArray result = DES.encode(key, input);
		for(int i = 0; i < output.size(); ++i)
			assertEquals(output.get(i), result.get(i));
	}

}
