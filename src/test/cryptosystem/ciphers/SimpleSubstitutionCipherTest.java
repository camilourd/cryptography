package test.cryptosystem.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;

import alphabet.alphabets.EnglishAlphabet;
import cryptosystem.ciphers.SimpleSubstitutionCipher;
import substitution.Substitution;
import test.cryptosystem.CryptosystemTest;

public class SimpleSubstitutionCipherTest extends CryptosystemTest {

	protected Substitution<Character, Character> substitution;
	
	public SimpleSubstitutionCipherTest() {
		super(new SimpleSubstitutionCipher(new EnglishAlphabet()), new EnglishAlphabet());
	}

	@Before
	public void setUp() throws Exception {
		String subs = "ztculqnoehijmpbxasfvkwdrgy";
		substitution = new Substitution<Character, Character>();
		for(int i = 0;i < subs.length(); ++i)
			substitution.add(alphabet.getCharacter(i), subs.charAt(i));
	}

	@Override
	public void encodeTest() {
		String message = "thisisatest";
		String output = ((SimpleSubstitutionCipher)cryptosystem).encode(substitution, message);
		for(int i = 0; i < message.length(); ++i)
			assertEquals(output.charAt(i), substitution.substitute(message.charAt(i)).charValue());
	}

	@Override
	public void decodeTest() {
		String message = "thisisatest";
		String output = ((SimpleSubstitutionCipher)cryptosystem).encode(substitution, message);			
	    assertEquals(message, ((SimpleSubstitutionCipher)cryptosystem).decode(substitution, output));
	}

}
