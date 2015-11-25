package cryptography.cryptosystem.ciphers.rsa;

import java.math.BigInteger;

import cryptography.alphabet.alphabets.ExtendedAlphabet;
import cryptography.substitution.AlphabetSubstitution;

public class RSASubstitution extends AlphabetSubstitution<String, BigInteger> {

	protected int digs = 1;
	protected BigInteger div;
	
	public RSASubstitution() {
		super(new ExtendedAlphabet());
		while(Math.pow(10, digs) <= alphabet.size()) ++digs;
		div = BigInteger.TEN.pow(digs);
	}

	public int getDigs() {
		return digs;
	}

	@Override
	public BigInteger substitute(String value) {
		String result = "";
		for(char car: value.toCharArray())
			result += parseString(car);
		return new BigInteger(result);
	}

	private String parseString(char car) {
		String result = "";
		int idx = alphabet.getIndex(car) + 1;
		for(int i = 0; i < digs; ++i, idx /= 10) {
			int d = idx % 10;
			result = ((char)('0' + d)) + result;
		}
		return result;
	}

	@Override
	public String restore(BigInteger value) {
		return value.toString();
	}

}
