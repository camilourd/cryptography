package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import cryptography.alphabet.alphabets.EnglishAlphabet;
import cryptography.cryptosystem.ciphers.VigenereCipher;
import test.cryptosystem.CryptosystemTest;

public class VigenereCipherTest extends CryptosystemTest<String, String> {

	public VigenereCipherTest() {
		super(new VigenereCipher(new EnglishAlphabet()));
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		String key = "thisakey";
		String output = cryptosystem.encode(key, message);
		
		for(int i = 0; i < message.length(); ++i)
			assertEquals(output.charAt(i), (char)alphabet.getElement(alphabet.getIndex(message.charAt(i)) + alphabet.getIndex(key.charAt(i % key.length()))));
	    assertEquals(message, cryptosystem.decode(key, output));
	}
	
}
