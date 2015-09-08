package test.cryptosystem;

import static org.junit.Assert.*;

import alphabet.LowerCaseEnglish;
import cryptosystem.Vigenere;
import tools.CharStream;

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
