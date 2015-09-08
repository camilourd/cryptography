package test.cryptosystem;

import static org.junit.Assert.*;

import edu.unal.crypto.alphabet.*;
import edu.unal.crypto.cryptosystem.*;
import edu.unal.crypto.tools.CharStream;

public class VigenereCipherTest extends CryptosystemTest {

	public VigenereCipherTest() {
		super(new Vigenere<Character>(new LowerCaseEnglish()));
	}

	@Override
	public void encodeDecodeTest() {

		Character[] key = CharStream.fromString("thisakey");
		
		@SuppressWarnings("unchecked")
		Vigenere<Character> algo = (Vigenere<Character>)cryptosystem;
		
		Character[] input = CharStream.fromString("thisisasimpletest");
		Character[] output = algo.encrypt(key, input);
		Character[] secret = algo.decrypt(key, output);
		
		assertEquals(CharStream.equals(input, secret), true);
	}	
}
