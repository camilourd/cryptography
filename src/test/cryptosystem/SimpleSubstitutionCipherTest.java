package test.cryptosystem;

import static org.junit.Assert.*;

import alphabet.LowerCaseEnglish;
import cryptosystem.Substitution;
import tools.CharStream;

public class SimpleSubstitutionCipherTest extends CryptosystemTest {

	public SimpleSubstitutionCipherTest() {
		super(new Substitution<Character, Character>(new LowerCaseEnglish(), new LowerCaseEnglish()));
	}

	@Override
	public void encodeDecodeTest() {
		
		String message = "thisisatest";
		Character[] input = CharStream.fromString(message);
		
		@SuppressWarnings("unchecked")
		Substitution<Character, Character> algo = (Substitution<Character, Character>)cryptosystem;
		
		String subs = "ztculqnoehijmpbxasfvkwdrgy";
		Substitution.Key<Character, Character> key = new Substitution.Key<Character, Character>();
		for(int i = 0;i < subs.length(); ++i) {
			key.set(algo.getInAlphabet().getValue(i), subs.charAt(i));
		}
		
		Character[] output = algo.encrypt(key, input);
		Character[] secret = algo.decrypt(key, output);
		
		assertEquals(CharStream.equals(input, secret), true);
	}
}
