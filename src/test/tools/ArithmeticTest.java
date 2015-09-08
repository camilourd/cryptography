package test.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.unal.crypto.tools.Arithmetic;

public class ArithmeticTest {

	@Test
	public void gcdTest() {
		assertEquals(3, Arithmetic.gcd(81, 57));
		assertEquals(3, Arithmetic.gcd(57, 81));
		assertEquals(1, Arithmetic.gcd(8, 1));
		assertEquals(1, Arithmetic.gcd(1, 8));
		assertEquals(70, Arithmetic.gcd(70, 70));
		assertEquals(5, Arithmetic.gcd(15, 25));
	}

}
