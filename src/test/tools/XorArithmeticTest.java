package test.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import math.XorArithmetic;

public class XorArithmeticTest {
	
	@Test
	public void multiplyTest() {
		assertEquals(11129, XorArithmetic.multiply(87, 131));
	}
	
	@Test
	public void modTest() {
		assertEquals(193, XorArithmetic.mod(11129, 283));
	}

}
