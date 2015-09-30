package cryptosystem.ciphers;

import alphabet.Alphabet;
import cryptosystem.Cryptosystem;

public class VigenereCipher extends Cryptosystem<String, String> {

	public VigenereCipher(Alphabet alphabet) {
		super(alphabet);
	}

	@Override
	public String encode(String key, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getCharacter(alphabet.getIndex(result[i]) + alphabet.getIndex(key.charAt(i % key.length())));
		return new String(result);
	}

	@Override
	public String decode(String key, String message) {
		char[] result = message.toCharArray();
		for(int i = 0; i < result.length; ++i)
			result[i] = alphabet.getCharacter(alphabet.getIndex(result[i]) - alphabet.getIndex(key.charAt(i % key.length())));
		return new String(result);
	}

	@Override
	public boolean isValidKey(String key) {
		return key.length() > 0;
	}

	@Override
	public String generateKey() {
		int lenght = 20 + (int)(Math.random() * 100);
		String key = "";
		for(int i = 0; i < lenght; ++i)
			key += alphabet.getCharacter((int)(Math.random() * alphabet.size()));
		return key;
	}

}
