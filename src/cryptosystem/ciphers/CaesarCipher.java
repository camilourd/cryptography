package cryptosystem.ciphers;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public class CaesarCipher extends Cryptosystem<Integer, String, Character> {

	public CaesarCipher(Alphabet<Character> alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Integer shift, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getElement(alphabet.getIndex(result[i]) + shift);
		return new String(result);
	}

	@Override
	public String decode(Integer shift, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getElement(alphabet.getIndex(result[i]) - shift);
		return new String(result);
	}

	@Override
	public boolean isValidKey(Integer key) {
		return true;
	}

	@Override
	public Integer generateKey() {
		return (int)(Math.random() * alphabet.size());
	}

}
