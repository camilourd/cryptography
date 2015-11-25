package cryptography.cryptosystem.ciphers.rsa;

import java.math.BigInteger;

import cryptography.alphabet.alphabets.ExtendedAlphabet;
import cryptography.substitution.AlphabetSubstitution;

public class NumberSubstitution extends AlphabetSubstitution<String, BigInteger> {

	protected int digs = 1;
	protected BigInteger div;
	
	public NumberSubstitution() {
		super(new ExtendedAlphabet());
		while(Math.pow(10, digs) <= alphabet.size()) ++digs;
		div = BigInteger.TEN.pow(digs);
	}

	@Override
	public BigInteger substitute(String value) {
		return new BigInteger(value);
	}

	@Override
	public String restore(BigInteger value) {
		String result = "";
		while(!value.equals(BigInteger.ZERO)) {
			int idx = value.mod(div).intValue();
			value = value.divide(div);
			result = alphabet.getElement(idx - 1) + result;
		}
		return result;
	}

}
