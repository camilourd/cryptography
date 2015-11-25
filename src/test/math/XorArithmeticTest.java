package test.math;

import static org.junit.Assert.*;

import org.junit.Test;

import cryptography.math.XorArithmetic;

public class XorArithmeticTest {
	
	@Test
	public void multiplyTest() {
		assertEquals(11129, XorArithmetic.multiply(87, 131));
	}
	
	@Test
	public void modTest() {
		assertEquals(193, XorArithmetic.mod(11129, 283));
	}
	
	@Test
	public void divideTest() {
		assertEquals(40, XorArithmetic.divide(11129, 283));
	}
	
	@Test
	public void xmultiplyByXTest() {
		assertEquals(254, XorArithmetic.xmultiply(87, 19, 283));
	}

}
