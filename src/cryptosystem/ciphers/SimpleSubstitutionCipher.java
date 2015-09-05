package cryptosystem.ciphers;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;
import substitution.Substitution;

public class SimpleSubstitutionCipher extends Cryptosystem<Substitution<Character, Character>, String> {

	public SimpleSubstitutionCipher(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Substitution<Character, Character> key, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = key.substitute(result[i]);
		return new String(result);
	}

	@Override
	public String decode(Substitution<Character, Character> key, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = key.restore(result[i]);
		return new String(result);
	}

	@Override
	public boolean isValidKey(Substitution<Character, Character> key) {
		for(int i = 0; i < alphabet.size(); ++i)
			if(key.substitute(alphabet.getCharacter(i)) == null)
				return false;
		return true;
	}

}
