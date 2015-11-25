package cryptography.cryptosystem.ciphers.des;

import cryptography.alphabet.Alphabet;
import cryptography.substitution.SubstitutionFunction;
import cryptography.tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class DESDecodingSubstitution implements SubstitutionFunction<String, BitArray> {

	protected Alphabet alphabet;
	protected int bits = 0;
	
	public DESDecodingSubstitution(Alphabet alphabet) {
		this.alphabet = alphabet;
		for(int c = 1; c < alphabet.size(); c <<= 1)
			++bits;
	}
	
	@Override
	public BitArray substitute(String value) {
		return BitArrayTools.hexStringToBitArray(value);
	}

	@Override
	public String restore(BitArray value) {
		String result = "";
		for(BitArray val: BitArrayTools.divide(value.size() / bits, value))
			result += alphabet.getElement(BitArrayTools.parseInt(val));
		return result;
	}

}
