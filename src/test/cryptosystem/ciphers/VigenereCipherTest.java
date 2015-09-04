package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.ciphers.VigenereCipher;
import test.cryptosystem.CryptosystemTest;

public class VigenereCipherTest extends CryptosystemTest {

	public VigenereCipherTest() {
		super(new VigenereCipher(new EnglishAlphabet()), new EnglishAlphabet());
	}

	@Override
	public void encodeTest() {
		String message = "thisisasimpletest";
		String key = "thisakey";
		String output = ((VigenereCipher)cryptosystem).encode(key, message);
		for(int i = 0; i < message.length(); ++i)
			assertEquals(output.charAt(i), alphabet.getCharacter(alphabet.getIndex(message.charAt(i)) + alphabet.getIndex(key.charAt(i % key.length()))));
	}

	@Override
	public void decodeTest() {
		String message = "thisisasimpletest";
		String key = "thisakey";
		String output = ((VigenereCipher)cryptosystem).encode(key, message);			
	    assertEquals(message, ((VigenereCipher)cryptosystem).decode(key, output));
	}
	
}
