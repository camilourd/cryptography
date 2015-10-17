package substitution;

import alphabet.Alphabet;

public abstract class AlphabetSubstitution<V, S, C> implements SubstitutionFunction<V, S> {
	
	protected Alphabet<C> alphabet;

	public AlphabetSubstitution(Alphabet<C> alphabet) {
		this.alphabet = alphabet;
	}

	public Alphabet<C> getAlphabet() {
		return alphabet;
	}

}
