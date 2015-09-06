package test.cryptosystem.ciphers;

import java.math.BigInteger;

import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.ciphers.RSA;
import test.cryptosystem.CryptosystemTest;

public class RSATest  extends CryptosystemTest {

	public RSATest() {
		super(new RSA(new EnglishAlphabet()), new EnglishAlphabet());
	}

	@Override
	public void encodeDecodeTest() {
		
		String message = "thisisasimpletest";
		RSA.Key key = new RSA.Key(BigInteger.valueOf(509), BigInteger.valueOf(673), BigInteger.valueOf(29));
		String output = ((RSA)cryptosystem).encode(key, message);
		assert(message.startsWith(((RSA)cryptosystem).decode(key, output)));		
	}
}
