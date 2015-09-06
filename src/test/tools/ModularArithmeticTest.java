package test.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import tools.ModularArithmetic;

public class ModularArithmeticTest {

	@Test
	public void modInverseTest() {
		assertEquals(21, ModularArithmetic.multiplicativeInverse(5, 26));
		assertEquals(5, ModularArithmetic.multiplicativeInverse(21, 26));
		assertEquals(7, ModularArithmetic.multiplicativeInverse(15, 26));
		assertEquals(15, ModularArithmetic.multiplicativeInverse(7, 26));
		assertEquals(4, ModularArithmetic.multiplicativeInverse(21, 27));
	}
	
	@Test
	public void powTest() {
		assertEquals(445, ModularArithmetic.pow(4, 13, 497));
		assertEquals(2790, ModularArithmetic.pow(65, 17, 3233));
		assertEquals(65, ModularArithmetic.pow(2790, 2753, 3233));
		assertEquals(4, ModularArithmetic.pow(2790, 53, 61));
		assertEquals(12, ModularArithmetic.pow(2790, 49, 53));
	}

}
