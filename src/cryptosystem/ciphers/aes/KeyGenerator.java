package cryptosystem.ciphers.aes;

import unalcol.types.collection.bitarray.BitArray;

public class KeyGenerator {
	
	protected static int nb = 4;
	protected static BitArray[] R = {
			new BitArray("00000000000000000000000000000000"),
			new BitArray("00000001000000000000000000000000"),
			new BitArray("00000010000000000000000000000000"),
			new BitArray("00000100000000000000000000000000"),
			new BitArray("00001000000000000000000000000000"),
			new BitArray("00010000000000000000000000000000"),
			new BitArray("00100000000000000000000000000000"),
			new BitArray("01000000000000000000000000000000"),
			new BitArray("10000000000000000000000000000000"),
			new BitArray("00011011000000000000000000000000"),
			new BitArray("00110110000000000000000000000000")
	};
	
	public static BitArray[] generate(BitArray key, StateSubstitution substitution) {
		int nk = key.size() / 32;
		BitArray[] w = new BitArray[nb * (nk + 7)];
		
		for(int i = 0; i < nk; ++i)
			w[i] = key.subBitArray(i * 32, (i + 1) * 32);
		for(int i = nk; i < w.length; ++i) {
			w[i] = (BitArray) w[i - 1].clone();
			if(i % nk == 0)
				w[i] = xor(subWord(rotWord(w[i]), substitution), R[i / nk]);
			else if(nk > 6 && (i % nk == 4))
				w[i] = subWord(w[i], substitution);
			w[i].xor(w[i - nk]);
		}
		return w;
	}

	private static BitArray xor(BitArray subWord, BitArray R) {
		subWord.xor(R);
		return subWord;
	}

	private static BitArray subWord(BitArray word, StateSubstitution substitution) {
		BitArray result = new BitArray("");
		int wordSize = word.size() / nb;
		for(int i = 0; i < nb; ++i)
			result.add(substitution.substitute(word.subBitArray(i * wordSize, (i + 1) * wordSize)));
		return result;
	}

	public static BitArray rotWord(BitArray word) {
		BitArray result = new BitArray("");
		int wordSize = word.size() / nb;
		result.add(word.subBitArray(wordSize));
		result.add(word.subBitArray(0, wordSize));
		return result;
	}
	
}
