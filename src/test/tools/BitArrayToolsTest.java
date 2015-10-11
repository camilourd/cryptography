package test.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class BitArrayToolsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseInt() {
		for(int i = 0; i < 16; ++i) {
			BitArray num = new BitArray(4, false);
			for(int j = 0; j < 4; ++j)
				num.set(j, ((i>>(3 - j)) & 1) > 0);
			System.out.println(num);
			for(int j = 0; j < 16; ++j)
				if(i == j)
					assertEquals(j, BitArrayTools.parseInt(num));
				else
					assertNotEquals(j, BitArrayTools.parseInt(num));
		}
	}

}
