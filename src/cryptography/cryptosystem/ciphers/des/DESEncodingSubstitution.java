package cryptography.cryptosystem.ciphers.des;

import cryptography.alphabet.Alphabet;
import cryptography.substitution.SubstitutionFunction;
import cryptography.tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class DESEncodingSubstitution  implements SubstitutionFunction<String, BitArray> {

	protected Alphabet alphabet;
	protected int bits = 0;
	
	public DESEncodingSubstitution(Alphabet alphabet) {
		this.alphabet = alphabet;
		for(int c = 1; c < alphabet.size(); c <<= 1)
			++bits;
	}

	@Override
	public BitArray substitute(String value) {
		BitArray result = new BitArray("");
		for(char car: value.toCharArray())
			result.add(BitArrayTools.parseBitArray(alphabet.getIndex(car), bits));
		return result;
	}

	@Override
	public String restore(BitArray value) {
		return BitArrayTools.toHexString(value);
	}
	
	public Alphabet getAlphabet() {
		return alphabet;
	}

}
