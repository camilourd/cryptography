package cryptography.substitution;

import cryptography.alphabet.Alphabet;

public abstract class AlphabetSubstitution<V, S> implements SubstitutionFunction<V, S> {
	
	protected Alphabet alphabet;

	public AlphabetSubstitution(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

}
