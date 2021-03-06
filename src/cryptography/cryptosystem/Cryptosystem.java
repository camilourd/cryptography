package cryptography.cryptosystem;

import cryptography.alphabet.Alphabet;

public abstract class Cryptosystem<K, M> implements CipherFunction<K, M> {
	
	protected Alphabet alphabet;
	
	public Cryptosystem(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	
	public Alphabet getAlphabet() {
		return alphabet;
	}
	
	public abstract boolean isValidKey(K key);
	public abstract K generateKey();

}
