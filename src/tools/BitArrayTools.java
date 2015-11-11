package tools;

import alphabet.alphabets.HexadecimalAlphabet;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.vector.Vector;

public class BitArrayTools {
	
	public static final HexadecimalAlphabet hexAlphabet = new HexadecimalAlphabet();
	
	public static int parseInt(BitArray bits) {
		int num = 0;
		for(int i = 0; i < bits.size(); ++i)
			num = (num << 1) | (bits.get(i)? 1:0);
		return num;
	}
	
	public static BitArray parseBitArray(int num, int bits) {
		BitArray res = new BitArray(bits, false);
		for(int i = 0; i < bits; ++i)
			res.set(bits - i - 1, (num & (1<<i)) > 0);
		return res;
	}
	
	public static BitArray generate(int lenght) {
		BitArray key = new BitArray(lenght, false);
		for(int i = 0; i < lenght; ++i)
			if(Math.random() < 0.5)
				key.set(i, true);
		return key;
	}
	
	public static String toHexString(BitArray num) {
		String result = "";
		int start = num.size() % 4;
		if(start > 0)
			result += hexAlphabet.getElement(parseInt(num.subBitArray(0, start)));
		for(; start < num.size(); start += 4)
			result += hexAlphabet.getElement(parseInt(num.subBitArray(start, start + 4)));
		return result;
	}
	
	public static BitArray hexStringToBitArray(String hex) {
		BitArray result = new BitArray("");
		for(int i = 0; i < hex.length(); ++i)
			result.add(parseBitArray(hexAlphabet.getIndex(hex.charAt(i)), 4));
		return result;
	}

	public static Vector<BitArray> divide(int n, BitArray values) {
		Vector<BitArray> parts = new Vector<BitArray>();
		int size = values.size() / n;
		for(int i = 0; i < n; ++i)
			parts.add(values.subBitArray(i * size, (i + 1) * size));
		return parts;
	}
	
}
