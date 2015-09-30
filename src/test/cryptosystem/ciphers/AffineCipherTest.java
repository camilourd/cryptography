package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import org.junit.Test;

import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.ciphers.AffineCipher;
import cryptosystem.types.Pair;
import math.Arithmetic;
import test.cryptosystem.CryptosystemTest;

public class AffineCipherTest extends CryptosystemTest {

	public AffineCipherTest() {
		super(new AffineCipher(new EnglishAlphabet()), new EnglishAlphabet());
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		for(int i = 0; i < alphabet.size(); ++i) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>((int) (Math.random() * 100), i);
			String output = ((AffineCipher)cryptosystem).encode(key, message);	
			assertEquals(message, ((AffineCipher)cryptosystem).decode(key, output));
			
			if(((AffineCipher)cryptosystem).isValidKey(key)) {
				assertNotEquals(message, output);
				for(int j = 0; j < message.length(); ++j)
					assertEquals(output.charAt(j), alphabet.getCharacter((key.first * alphabet.getIndex(message.charAt(j))) + key.second));
			}
			else
				assertEquals(message, output);
		}
	}
	
	@Test
	public void isValidKeyTest() {
		for(int i = 0; i < alphabet.size(); ++i) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>((int) (Math.random() * 100), i);
			assertEquals(((AffineCipher)cryptosystem).isValidKey(key), Arithmetic.gcd(key.first, alphabet.size()) == 1);
		}
	}

}
