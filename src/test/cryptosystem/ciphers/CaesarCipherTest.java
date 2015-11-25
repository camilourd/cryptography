package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import cryptography.alphabet.alphabets.EnglishAlphabet;
import cryptography.cryptosystem.ciphers.CaesarCipher;
import test.cryptosystem.CryptosystemTest;

public class CaesarCipherTest extends CryptosystemTest<Integer, String> {
	
	ArrayList<Integer> shifts;
	
	public CaesarCipherTest() {
		super(new CaesarCipher(new EnglishAlphabet()));
		shifts = new ArrayList<Integer>();
		shifts.add(0); shifts.add(-1); shifts.add(2);
		shifts.add(10); shifts.add(-38); shifts.add(107);
	}

	@Override
	public void encodeDecodeTest() {
		String message = "thisisatest";
		
		for(Integer shift: shifts) {
			String output = cryptosystem.encode(shift, message);
			testEquality(message, output, shift);
			assertEquals(message, cryptosystem.decode(shift, output));
		}
	}

	public void testEquality(String message, String output, int shift) {
		for(int i = 0; i < message.length(); ++i)
			assertEquals((char)alphabet.getElement(alphabet.getIndex(message.charAt(i)) + shift), output.charAt(i));
	}

}
