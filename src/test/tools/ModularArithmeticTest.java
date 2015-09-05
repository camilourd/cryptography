package test.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tools.ModularArithmetic;

public class ModularArithmeticTest {

	@Test
	public void modInverseTest() {
		assertEquals(21, ModularArithmetic.modInverse(5, 26));
		assertEquals(5, ModularArithmetic.modInverse(21, 26));
		assertEquals(7, ModularArithmetic.modInverse(15, 26));
		assertEquals(15, ModularArithmetic.modInverse(7, 26));
		assertEquals(4, ModularArithmetic.modInverse(21, 27));
	}
	
	

}
