package test.cryptosystem;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import alphabet.LowerCaseEnglish;
import cryptosystem.RSA;
import tools.CharStream;

public class RSATest  extends CryptosystemTest {

	public RSATest() {
		super(new RSA<Character>(new LowerCaseEnglish()));
	}

	@Override
	public void encodeDecodeTest() {
				
		RSA.Key key = new RSA.Key(BigInteger.valueOf(509), BigInteger.valueOf(673), BigInteger.valueOf(29));
		@SuppressWarnings("unchecked")
		RSA<Character> algo = (RSA<Character>)cryptosystem;
		
		Character[] input = CharStream.fromString("testingrsacryptosystem");
		BigInteger[] output = algo.encrypt(key, input);
		Character[] secret = algo.decrypt(key, output);
		
		assertEquals(CharStream.equals(input, secret), true);
	}
}
