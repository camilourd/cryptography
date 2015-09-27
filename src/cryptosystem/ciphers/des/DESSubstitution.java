package cryptosystem.ciphers.des;

import substitution.SubstitutionFunction;
import unalcol.types.collection.bitarray.BitArray;

public class DESSubstitution implements SubstitutionFunction<BitArray, BitArray> {

	protected byte[] replacements;
	
	public DESSubstitution(byte[] replacements) {
		this.replacements = replacements;
	}
	
	public BitArray substitute(BitArray value) {
		BitArray result = (BitArray) value.clone();
		for(int i = 0;i < replacements.length; ++i)
			result.set(i, value.get(replacements[i]));
		return result;
	}

	@Override
	public BitArray restore(BitArray value) {
		return value;
	}

}
