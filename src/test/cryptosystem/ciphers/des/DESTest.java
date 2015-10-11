package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cryptosystem.ciphers.des.DES;
import unalcol.types.collection.bitarray.BitArray;

public class DESTest {
	
	public BitArray key, input, output;
	public DES des;
	

	@Before
	public void setUp() throws Exception {
		des = new DES();
		key = des.generateKey();
		input = new BitArray("0000000100100011010001010110011110001001101010111100110111101111");
	}

	@Test
	public void test() {
		BitArray result = des.encode(key, input);
		System.out.println(key);
		assertNotEquals(input.toString(), result.toString());
		result = des.decode(key, result);
		assertEquals(input.toString(), result.toString());
	}

}
