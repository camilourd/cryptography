package cryptography.cryptosystem.ciphers;

import cryptography.alphabet.Alphabet;
import cryptography.alphabet.alphabets.StringAlphabet;
import cryptography.cryptosystem.Cryptosystem;
import cryptography.substitution.Substitution;
import cryptography.tools.ArrayTools;

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
			if(key.substitute(alphabet.getElement(i)) == null)
				return false;
		return true;
	}

	@Override
	public Substitution<Character, Character> generateKey() {
		char[] cars = ((StringAlphabet)alphabet).getCharacters();
		ArrayTools.mixElements(cars);
		Substitution<Character, Character> substitution = new Substitution<Character, Character>();
		for(int i = 0;i < cars.length; ++i)
			substitution.add(alphabet.getElement(i), cars[i]);
		return substitution;
	}

}
