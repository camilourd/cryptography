package test.cryptosystem.ciphers.elgamal;

import static org.junit.Assert.*;

import java.math.BigInteger;

import cryptosystem.ciphers.elgamal.ElGamal;
import cryptosystem.ciphers.elgamal.key.ElGamalKey;
import test.cryptosystem.CryptosystemTest;
import tools.MathTools;
import types.Pair;

public class ElGamalTest extends CryptosystemTest<ElGamalKey, Pair<BigInteger, BigInteger>> {

	public ElGamalTest() {
		super(new ElGamal());
	}

	@Override
	public void encodeDecodeTest() {
		runTest(new BigInteger("961748941"),
				new BigInteger("134567"),
				new BigInteger("5487231"));
	}
	
	public void runTest(BigInteger p, BigInteger g, BigInteger b) {
		ElGamalKey key = new ElGamalKey(p, g, b);
		BigInteger message = MathTools.generateBigInteger(p.toString().length()).mod(p);
		
		Pair<BigInteger, BigInteger> input = new Pair<BigInteger, BigInteger>(BigInteger.ONE.negate(), message);
		System.out.println("input:" + message);
		
		Pair<BigInteger, BigInteger> encoding = cryptosystem.encode(key, input);
		System.out.println("encoding: ");
		System.out.println("    g^k mod p: " + encoding.first);
		System.out.println("    encoding:  " + encoding.second);
		assertNotEquals(input.first, encoding.first);
		assertNotEquals(input.second, encoding.second);
		
		Pair<BigInteger, BigInteger> decoding = cryptosystem.decode(key, encoding);
		System.out.println("decoding: ");
		System.out.println("    g^k mod p: " + decoding.first);
		System.out.println("    dencoding:  " + decoding.second);
		assertEquals(encoding.first, decoding.first);
		assertEquals(message, decoding.second);
	}

	

}
