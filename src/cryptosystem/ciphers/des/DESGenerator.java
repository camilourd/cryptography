package cryptosystem.ciphers.des;

import unalcol.types.collection.bitarray.BitArray;

public class DESGenerator {
	
	public final static DESSubstitution PC1 = new DESSubstitution(new byte[] {
			56, 48, 40, 32, 24, 16, 8,
	        0,  57, 59, 41, 33, 25, 17,
	        9, 1,  58, 50, 42, 34, 26,
	        18, 10, 2,  59, 51, 43, 35,
	        62, 54, 46, 38, 30, 22, 14,
	        6,  61, 53, 45, 37, 29, 21,
	        13, 5,  60, 52, 44, 36, 28,
	        20, 12, 4,  27, 19, 11, 3
		});
	
	public final static DESSubstitution PC2 = new DESSubstitution(new byte[] {
			13, 16, 10, 23, 0,  4,
	        2,  27, 14, 5,  20, 9,
	        22, 18, 11, 3,  25, 7,
	        15, 6,  26, 19, 12, 1,
	        40, 51, 30, 36, 46, 54,
	        29, 39, 50, 44, 32, 47,
	        43, 48, 38, 55, 33, 52,
	        45, 41, 49, 35, 28, 31
		});
	
	public final static int[] rotations = new int[] {
			1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
		};
	
	
	public static BitArray[] generateKeys(BitArray key) {
		BitArray[] parts = DESGenerator.divide(2, (PC1.substitute(key)).subBitArray(0, 56));
		BitArray[] keys = new BitArray[rotations.length];
		for(int i = 0; i < rotations.length; ++i)
			keys[i] = nextKey(i, parts[0], parts[1]);
		return keys;
	}

	public static BitArray[] divide(int n, BitArray values) {
		BitArray[] parts = new BitArray[n];
		int size = values.size() / n;
		for(int i = 0; i < n; ++i)
			parts[i] = values.subBitArray(i * size, (i + 1) * size);
		return parts;
	}
	
	public static void leftRotated(BitArray values) {
		boolean first = values.get(0);
		values.leftShift(1);
		values.set(values.size() - 1, first);
	}
	
	private static BitArray nextKey(int i, BitArray left, BitArray right) {
		for(int j = 0; j < rotations[i]; ++j) {
			leftRotated(left);
			leftRotated(right);
		}
		BitArray result = (BitArray) left.clone();
		result.add(right);
		return PC2.substitute(result).subBitArray(0, 48);
	}

}
