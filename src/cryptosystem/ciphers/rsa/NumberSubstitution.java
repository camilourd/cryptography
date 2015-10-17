package cryptosystem.ciphers.rsa;

import java.math.BigInteger;

import alphabet.alphabets.NaturalAlphabet;
import substitution.AlphabetSubstitution;

public class NumberSubstitution extends AlphabetSubstitution<String, BigInteger> {

	public NumberSubstitution() {
		super(new NaturalAlphabet());
	}

	@Override
	public BigInteger substitute(String value) {
		return new BigInteger(value);
	}

	@Override
	public String restore(BigInteger value) {
		return value.toString();
	}

}
