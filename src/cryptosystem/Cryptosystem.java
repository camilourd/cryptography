package cryptosystem;

import alphabet.Alphabet;

public abstract class Cryptosystem<K, M, C> implements CipherFunction<K, M> {
	
	protected Alphabet<C> alphabet;
	
	public Cryptosystem(Alphabet<C> alphabet) {
		this.alphabet = alphabet;
	}
	
	public Alphabet<C> getAlphabet() {
		return alphabet;
	}
	
	public abstract boolean isValidKey(K key);
	public abstract K generateKey();

}
