package tools;

import unalcol.types.collection.bitarray.BitArray;

public class BitArrayTools {
	
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
			result += getHexCar(parseInt(num.subBitArray(0, start)));
		for(; start < num.size(); start += 4)
			result += getHexCar(parseInt(num.subBitArray(start, start + 4)));
		return result;
	}
	
	public static char getHexCar(int dig) {
		if(dig > 9)
			return (char)('a' + (dig - 10));
		return (char)('0' + dig);
	}
	
	public static BitArray[] divide(int n, BitArray values) {
		BitArray[] parts = new BitArray[n];
		int size = values.size() / n;
		for(int i = 0; i < n; ++i)
			parts[i] = values.subBitArray(i * size, (i + 1) * size);
		return parts;
	}
	
}
