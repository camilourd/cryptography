package cryptosystem.ciphers.des;

import alphabet.alphabets.CharacterAlphabet;
import substitution.AlphabetSubstitution;
import tools.BitArrayTools;
import unalcol.types.collection.bitarray.BitArray;

public class StringBitArraySubstitution extends AlphabetSubstitution<String, BitArray, Character> {

	protected int bits = 0;
	
	public StringBitArraySubstitution(CharacterAlphabet alphabet) {
		super(alphabet);
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
		String result = "";
		for(BitArray val: BitArrayTools.divide(value.size() / bits, value))
			result += alphabet.getElement(BitArrayTools.parseInt(val));
		return result;
	}

	public int getBits() {
		return bits;
	}

}
