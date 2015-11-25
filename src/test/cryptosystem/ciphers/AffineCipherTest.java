package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import org.junit.Test;

import cryptography.alphabet.alphabets.EnglishAlphabet;
import cryptography.cryptosystem.ciphers.AffineCipher;
import cryptography.math.Arithmetic;
import cryptography.types.Pair;
import test.cryptosystem.CryptosystemTest;

public class AffineCipherTest extends CryptosystemTest<Pair<Integer, Integer>, String> {

	public AffineCipherTest() {
		super(new AffineCipher(new EnglishAlphabet()));
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisasimpletest";
		for(int i = 0; i < alphabet.size(); ++i) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>((int) (Math.random() * 100), i);
			String output = cryptosystem.encode(key, message);	
			assertEquals(message, cryptosystem.decode(key, output));
			
			if(cryptosystem.isValidKey(key))
				for(int j = 0; j < message.length(); ++j)
					assertEquals(output.charAt(j), (char)alphabet.getElement((key.first * alphabet.getIndex(message.charAt(j))) + key.second));
			else
				assertEquals(message, output);
		}
	}
	
	@Test
	public void isValidKeyTest() {
		for(int i = 0; i < alphabet.size(); ++i) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>((int) (Math.random() * 100), i);
			assertEquals(cryptosystem.isValidKey(key), Arithmetic.gcd(key.first, alphabet.size()) == 1);
		}
	}

}
