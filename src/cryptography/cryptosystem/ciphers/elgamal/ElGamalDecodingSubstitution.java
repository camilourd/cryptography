package cryptography.cryptosystem.ciphers.elgamal;

import java.math.BigInteger;

import cryptography.alphabet.Alphabet;
import cryptography.substitution.AlphabetSubstitution;
import cryptography.types.Pair;

public class ElGamalDecodingSubstitution extends AlphabetSubstitution<String, Pair<BigInteger, BigInteger>> {

	protected int digs = 1;
	protected BigInteger div;
	
	public ElGamalDecodingSubstitution(Alphabet alphabet) {
		super(alphabet);
		while(Math.pow(10, digs) <= alphabet.size()) ++digs;
		div = BigInteger.TEN.pow(digs);
	}

	@Override
	public Pair<BigInteger, BigInteger> substitute(String value) {
		String[] parts = value.split("\\.", 2);
		return new Pair<BigInteger, BigInteger>(
					new BigInteger(parts[0]),
					new BigInteger(parts[1])
				);
	}

	@Override
	public String restore(Pair<BigInteger, BigInteger> value) {
		String result = "";
		while(value.second.signum() > 0) {
			int idx = value.second.mod(div).intValue();
			value.second = value.second.divide(div);
			result = alphabet.getElement(idx - 1) + result;
		}
		return result;
	}

}
