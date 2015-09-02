package cryptosystem.ciphers;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public class CaesarCipher extends Cryptosystem<Integer, String> {

	public CaesarCipher(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(Integer shift, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getCharacter(alphabet.getIndex(result[i]) + shift);
		return new String(result);
	}

	@Override
	public String decode(Integer shift, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getCharacter(alphabet.getIndex(result[i]) - shift);
		return new String(result);
	}

}
