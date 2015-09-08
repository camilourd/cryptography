package test.cryptosystem;

import static org.junit.Assert.*;

import alphabet.LowerCaseEnglish;
import cryptosystem.Hill;
import flanagan.math.Matrix;
import tools.CharStream;

public class HillCipherTest extends CryptosystemTest {

	public HillCipherTest() {
		super(new Hill<Character>(new LowerCaseEnglish()));
	}

	@Override
	public void encodeDecodeTest() {
		
		String message = "thisisasimpletest";
		Character[] input = CharStream.fromString(message);
		
		Matrix key = new Matrix(3, 3);
		key.setElement(0, 0, 5); key.setElement(0, 1, 17); key.setElement(0, 2, 20);
		key.setElement(1, 0, 9); key.setElement(1, 1, 23); key.setElement(1, 2, 3);
		key.setElement(2, 0, 2); key.setElement(2, 1, 11); key.setElement(2, 2, 13);
		
		@SuppressWarnings("unchecked")
		Hill<Character> algo = ((Hill<Character>)cryptosystem);
		Character[] output = algo.encrypt(key, input);
		Character[] secret = algo.decrypt(key, output);
		assertEquals(CharStream.equals(input, secret), true);
	}
}
