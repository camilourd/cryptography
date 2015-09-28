package test.cryptosystem.ciphers.des;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cryptosystem.ciphers.des.SimplifiedDES;
import unalcol.types.collection.bitarray.BitArray;

public class SimplifiedDESTest {
	
	public BitArray key, input, output;

	@Before
	public void setUp() throws Exception {
		key = new BitArray("111000111");
		input = new BitArray("100010110101");
		output = new BitArray("001101001010");
	}

	@Test
	public void test() {
		BitArray result = SimplifiedDES.encrypt(key, input);
		for(int i = 0; i < output.size(); ++i)
			assertEquals(output.get(i), result.get(i));
		result = SimplifiedDES.decrypt(key, output);
		for(int i = 0; i < input.size(); ++i)
			assertEquals(input.get(i), result.get(i));
	}

}
