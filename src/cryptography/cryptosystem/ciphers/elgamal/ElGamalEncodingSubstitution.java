package cryptography.cryptosystem.ciphers.elgamal;

import java.math.BigInteger;

import cryptography.alphabet.Alphabet;
import cryptography.substitution.AlphabetSubstitution;
import cryptography.types.Pair;

public class ElGamalEncodingSubstitution extends AlphabetSubstitution<String, Pair<BigInteger, BigInteger>> {

	protected int digs = 1;
	protected BigInteger div;
	
	public ElGamalEncodingSubstitution(Alphabet alphabet) {
		super(alphabet);
		while(Math.pow(10, digs) <= alphabet.size()) ++digs;
		div = BigInteger.TEN.pow(digs);
	}

	@Override
	public Pair<BigInteger, BigInteger> substitute(String value) {
		return new Pair<BigInteger, BigInteger>(BigInteger.ONE, calculateBigInteger(value));
	}
	
	public BigInteger calculateBigInteger(String value) {
		String result = "";
		for(char c: value.toCharArray())
			result += parseString(alphabet.getIndex(c) + 1);
		return new BigInteger(result);
	}
	
	public String parseString(int idx) {
		String result = "";
		for(int i = 0; i < digs; ++i, idx /= 10) {
			int d = idx % 10;
			result = ((char)('0' + d)) + result;
		}
		return result;
	}

	@Override
	public String restore(Pair<BigInteger, BigInteger> value) {
		return value.first + "." + value.second;
	}

}
