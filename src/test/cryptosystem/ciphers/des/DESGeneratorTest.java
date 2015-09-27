package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cryptosystem.ciphers.des.DESGenerator;
import unalcol.types.collection.bitarray.BitArray;

public class DESGeneratorTest {

	public BitArray[] keys;
	public BitArray key;
	
	@Before
	public void setUp() throws Exception {
		key = new BitArray("0001001100110100010101110111100110011011101111001101111111110001");
		keys = new BitArray[16];
		keys[0] = new BitArray("000110110000001011101111111111000111000001110010");
		keys[1] = new BitArray("011110011010111011011001110110111100100111100101");
		keys[2] = new BitArray("010101011111110010001010010000101100111110011001");
		keys[3] = new BitArray("011100101010110111010110110110110011010100011101");
		keys[4] = new BitArray("011111001110110000000111111010110101001110101000");
		keys[5] = new BitArray("011000111010010100111110010100000111101100101111");
		keys[6] = new BitArray("111011001000010010110111111101100001100010111100");
		keys[7] = new BitArray("111101111000101000111010110000010011101111111011");
		keys[8] = new BitArray("111000001101101111101011111011011110011110000001");
		keys[9] = new BitArray("101100011111001101000111101110100100011001001111");
		keys[10] = new BitArray("001000010101111111010011110111101101001110000110");
		keys[11] = new BitArray("011101010111000111110101100101000110011111101001");
		keys[12] = new BitArray("100101111100010111010001111110101011101001000001");
		keys[13] = new BitArray("010111110100001110110111111100101110011100111010");
		keys[14] = new BitArray("101111111001000110001101001111010011111100001010");
		keys[15] = new BitArray("110010110011110110001011000011100001011111110101");
	}

	@Test
	public void test() {
		BitArray[] result = DESGenerator.generateKeys(key);
		for(int i = 0; i < keys.length; ++i) {
			for(int j = 0; j < keys[i].size(); ++j)
				assertEquals(keys[i].get(j), result[i].get(j));
		}
	}

}
