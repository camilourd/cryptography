package cryptosystem;

import alphabet.Alphabet;

public abstract class Cryptosystem {
	
	Alphabet alphabet;
	
	public Cryptosystem(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	
	public abstract String encode(String key, String message);
	public abstract String decode(String key, String message);

}
